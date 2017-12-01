package cn.followtry.logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginBuilderAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginBuilderFactory;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.layout.AbstractStringLayout;
import org.apache.logging.log4j.core.layout.HtmlLayout;
import org.apache.logging.log4j.core.util.Transform;
import org.apache.logging.log4j.util.Strings;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.management.ManagementFactory;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@Plugin(

        name = "customHtmlLayout",
        category = "Core",
        elementType = "layout",
        printObject = true)
/**
 * Created by followtry on 2017/4/26.
 */
public class CustomHtmlLayout extends AbstractStringLayout {
  public static final String DEFAULT_FONT_FAMILY = "arial,sans-serif";
  private static final String TRACE_PREFIX = "<br />&nbsp;&nbsp;&nbsp;&nbsp;";
  private static final String REGEXP;
  private static final String DEFAULT_TITLE = "Log4j Log Messages";
  private static final String DEFAULT_CONTENT_TYPE = "text/html";
  private final long jvmStartTime;
  private final boolean locationInfo;
  private final String title;
  private final String contentType;
  private final String font;
  private final String fontSize;
  private final String headerSize;

  private CustomHtmlLayout(boolean locationInfo,String title,String contentType,Charset charset,
                           String font,String fontSize,String headerSize) {
    super(charset);
    this.jvmStartTime = ManagementFactory.getRuntimeMXBean().getStartTime();
    this.locationInfo = locationInfo;
    this.title = title;
    this.contentType = this.addCharsetToContentType(contentType);
    this.font = font;
    this.fontSize = fontSize;
    this.headerSize = headerSize;
  }

  public String getTitle() {
    return this.title;
  }

  public boolean isLocationInfo() {
    return this.locationInfo;
  }

  private String addCharsetToContentType(String contentType) {
    return contentType == null ? "text/html; charset=" + this.getCharset() : (contentType
            .contains("charset") ? contentType : contentType + "; charset=" + this.getCharset());
  }

  @Override
  public String toSerializable(LogEvent event) {
    StringBuilder sbuf = getStringBuilder();

    String formattedMessage = event.getMessage().getFormattedMessage();
    //将文本格式化为多行
    List<JSONObject> jsonObjects = getJsonOrDefault(formattedMessage);
    if (jsonObjects != null && jsonObjects.size() > 0) {
      int index =0;
      for (JSONObject jsonObject : jsonObjects) {

        sbuf.append(Strings.LINE_SEPARATOR).append("<tr>").append(Strings.LINE_SEPARATOR);

        //第零列
        sbuf.append("<td >");
        sbuf.append("<font color=\"#3385ff\"><h3>");
        sbuf.append(Transform.escapeHtmlTags(String.valueOf(++index)));
        sbuf.append("</h3></font>");
        sbuf.append("</td>").append(Strings.LINE_SEPARATOR);

        //第一列
        sbuf.append("<td>");
        sbuf.append(TimeUtils.formatLocalDateTime(jsonObject.getLongValue("date")));
        sbuf.append("</td>").append(Strings.LINE_SEPARATOR);

        //第二列
        String escapedThread = Transform.escapeHtmlTags(jsonObject.getString("threadName"));
        sbuf.append("<td title=\"").append(escapedThread).append(" thread\">");
        sbuf.append(escapedThread);
        sbuf.append("</td>").append(Strings.LINE_SEPARATOR);

        Level level = Level.toLevel(jsonObject.getString("level"),Level.DEBUG);
        //第三列
        sbuf.append("<td title=\"Level\">");
        if (level.equals(Level.DEBUG)) {
          sbuf.append("<font color=\"#339933\">");
          sbuf.append(Transform.escapeHtmlTags(String.valueOf(level)));
          sbuf.append("</font>");
        } else if (level.isMoreSpecificThan(Level.WARN)) {
          sbuf.append("<font color=\"#993300\"><strong>");
          sbuf.append(Transform.escapeHtmlTags(String.valueOf(level)));
          sbuf.append("</strong></font>");
        } else {
          sbuf.append(Transform.escapeHtmlTags(String.valueOf(level)));
        }
        sbuf.append("</td>").append(Strings.LINE_SEPARATOR);

        //第四列
        String escapedLogger = Transform.escapeHtmlTags(jsonObject.getString("loggerName"));
        if (escapedLogger.isEmpty()) {
          escapedLogger = "root";
        }

        sbuf.append("<td title=\"").append(escapedLogger).append(" logger\">");
        sbuf.append(escapedLogger);
        sbuf.append("</td>").append(Strings.LINE_SEPARATOR);
        if (this.locationInfo) {
          StackTraceElement throwable = event.getSource();
          sbuf.append("<td>");
          sbuf.append(Transform.escapeHtmlTags(throwable.getFileName()));
          sbuf.append(':');
          sbuf.append(throwable.getLineNumber());
          sbuf.append("</td>").append(Strings.LINE_SEPARATOR);
        }

        //第五列
        sbuf.append("<td title=\"信息\">");
        String formatJson = FormatUtil.formatJson(jsonObject.getString("message")).replaceAll(REGEXP,"<br />");
        String message = Transform.escapeHtmlTags(formatJson).replaceAll(REGEXP,"<br />");
        if (level == Level.ERROR) {

          sbuf.append("<font color=\"#FF0000\">");
          sbuf.append(formatJson);
          sbuf.append("</font>");
        }else if (level == Level.WARN){
          sbuf.append("<font color=\"#D9D919\">");
          sbuf.append(formatJson);
          sbuf.append("</font>");
        }else{
          sbuf.append(formatJson);
        }
        sbuf.append("</td>").append(Strings.LINE_SEPARATOR);
        sbuf.append("</tr>").append(Strings.LINE_SEPARATOR);

      }
    } else {
      sbuf.append(Strings.LINE_SEPARATOR).append("<tr>").append(Strings.LINE_SEPARATOR);
      //第一列
      sbuf.append("<td>");
      sbuf.append(event.getTimeMillis() - this.jvmStartTime);
      sbuf.append("</td>").append(Strings.LINE_SEPARATOR);

      //第二列
      String escapedThread = Transform.escapeHtmlTags(event.getThreadName());
      sbuf.append("<td title=\"").append(escapedThread).append(" thread\">");
      sbuf.append(escapedThread);
      sbuf.append("</td>").append(Strings.LINE_SEPARATOR);
      //第三列
      sbuf.append("<td title=\"Level\">");
      if (event.getLevel().equals(Level.DEBUG)) {
        sbuf.append("<font color=\"#339933\">");
        sbuf.append(Transform.escapeHtmlTags(String.valueOf(event.getLevel())));
        sbuf.append("</font>");
      } else if (event.getLevel().isMoreSpecificThan(Level.WARN)) {
        sbuf.append("<font color=\"#993300\"><strong>");
        sbuf.append(Transform.escapeHtmlTags(String.valueOf(event.getLevel())));
        sbuf.append("</strong></font>");
      } else {
        sbuf.append(Transform.escapeHtmlTags(String.valueOf(event.getLevel())));
      }

      sbuf.append("</td>").append(Strings.LINE_SEPARATOR);
      //第四列
      String escapedLogger = Transform.escapeHtmlTags(event.getLoggerName());
      if (escapedLogger.isEmpty()) {
        escapedLogger = "root";
      }

      sbuf.append("<td title=\"").append(escapedLogger).append(" logger\">");
      sbuf.append(escapedLogger);
      sbuf.append("</td>").append(Strings.LINE_SEPARATOR);
      if (this.locationInfo) {
        StackTraceElement throwable = event.getSource();
        sbuf.append("<td>");
        sbuf.append(Transform.escapeHtmlTags(throwable.getFileName()));
        sbuf.append(':');
        sbuf.append(throwable.getLineNumber());
        sbuf.append("</td>").append(Strings.LINE_SEPARATOR);
      }

      //第五列
      sbuf.append("<td title=\"Message\">");

      sbuf.append(Transform.escapeHtmlTags(event.getMessage().getFormattedMessage()).replaceAll
              (REGEXP,"<br />"));
      sbuf.append("</td>").append(Strings.LINE_SEPARATOR);
      sbuf.append("</tr>").append(Strings.LINE_SEPARATOR);
      if (event.getContextStack() != null && !event.getContextStack().isEmpty()) {
        sbuf.append("<tr><td bgcolor=\"#EEEEEE\" style=\"font-size : ").append(this.fontSize);
        sbuf.append(";\" colspan=\"6\" ");
        sbuf.append("title=\"Nested Diagnostic Context\">");
        sbuf.append("NDC: ").append(Transform.escapeHtmlTags(event.getContextStack().toString()));
        sbuf.append("</td></tr>").append(Strings.LINE_SEPARATOR);
      }

      if (event.getContextData() != null && !event.getContextData().isEmpty()) {
        sbuf.append("<tr><td bgcolor=\"#EEEEEE\" style=\"font-size : ").append(this.fontSize);
        sbuf.append(";\" colspan=\"6\" ");
        sbuf.append("title=\"Mapped Diagnostic Context\">");
        sbuf.append("MDC: ").append(Transform.escapeHtmlTags(event.getContextData().toMap()
                .toString()));
        sbuf.append("</td></tr>").append(Strings.LINE_SEPARATOR);
      }

      Throwable throwable1 = event.getThrown();
      if (throwable1 != null) {
        sbuf.append("<tr><td bgcolor=\"#993300\" style=\"color:White; font-size : ").append(this
                .fontSize);
        sbuf.append(";\" colspan=\"6\">");
        this.appendThrowableAsHtml(throwable1,sbuf);
        sbuf.append("</td></tr>").append(Strings.LINE_SEPARATOR);

      }
    }

    return sbuf.toString();
  }

  private List<JSONObject> getJsonOrDefault(String formattedMessage) {
    List<JSONObject> jsonObjects = null;
    try {
      jsonObjects = JSON.parseArray(formattedMessage,JSONObject.class);
    } catch (Exception e) {
      //不处理解析JSON异常的日志
    }
    return jsonObjects == null ? Collections.emptyList() : jsonObjects;
  }

  @Override
  public String getContentType() {
    return this.contentType;
  }

  private void appendThrowableAsHtml(Throwable throwable,StringBuilder sbuf) {
    StringWriter sw = new StringWriter();
    PrintWriter pw = new PrintWriter(sw);

    try {
      throwable.printStackTrace(pw);
    } catch (RuntimeException var10) {
      ;
    }

    pw.flush();
    LineNumberReader reader = new LineNumberReader(new StringReader(sw.toString()));
    ArrayList lines = new ArrayList();

    try {
      for (String first = reader.readLine(); first != null; first = reader.readLine()) {
        lines.add(first);
      }
    } catch (IOException var11) {
      if (var11 instanceof InterruptedIOException) {
        Thread.currentThread().interrupt();
      }

      lines.add(var11.toString());
    }

    boolean first1 = true;
    Iterator iter = lines.iterator();

    while (iter.hasNext()) {
      String line = (String)iter.next();
      if (!first1) {
        sbuf.append("<br />&nbsp;&nbsp;&nbsp;&nbsp;");
      } else {
        first1 = false;
      }

      sbuf.append(Transform.escapeHtmlTags(line));
      sbuf.append(Strings.LINE_SEPARATOR);
    }

  }

  private StringBuilder appendLs(StringBuilder sbuilder,String s) {
    sbuilder.append(s).append(Strings.LINE_SEPARATOR);
    return sbuilder;
  }

  private StringBuilder append(StringBuilder sbuilder,String s) {
    sbuilder.append(s);
    return sbuilder;
  }

  @Override
  public byte[] getHeader() {
    StringBuilder sbuf = new StringBuilder();
    this.append(sbuf,"<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" ");
    this.appendLs(sbuf,"\"http://www.w3.org/TR/html4/loose.dtd\">");
    this.appendLs(sbuf,"<html>");
    this.appendLs(sbuf,"<head>");
    this.append(sbuf,"<meta charset=\"");
    this.append(sbuf,this.getCharset().toString());
    this.appendLs(sbuf,"\"/>");
    this.append(sbuf,"<title>").append(this.title);
    this.appendLs(sbuf,"</title>");
    this.appendLs(sbuf,"<style type=\"text/css\">");
    this.appendLs(sbuf,"<!--");
    this.append(sbuf,"body, table {font-family:").append(this.font).append("; font-size: ");
    this.appendLs(sbuf,this.headerSize).append(";}");
    this.appendLs(sbuf,"th {background: #336699; color: #FFFFFF; text-align: left;}");
    this.appendLs(sbuf,"-->");
    this.appendLs(sbuf,"</style>");
    this.appendLs(sbuf,"</head>");
    this.appendLs(sbuf,"<body bgcolor=\"#FFFFFF\" topmargin=\"6\" leftmargin=\"6\">");
    this.appendLs(sbuf,"<hr size=\"1\" noshade=\"noshade\">");
    this.appendLs(sbuf,"<h2><font size=\"14\" color=\"#FF0000\">");
    this.appendLs(sbuf,"日志发送时间：" + TimeUtils.formatLocalDateTime(System.currentTimeMillis())
            + "<br>");
    this.appendLs(sbuf,"</font></h2>");
    this.appendLs(sbuf,"<br>");
    this.appendLs(sbuf,"<table cellspacing=\"0\" cellpadding=\"4\" border=\"1\" " +
            "bordercolor=\"#efeff1\" width=\"100%\">");
    this.appendLs(sbuf,"<tr>");
    this.appendLs(sbuf,"<th>序号</th>");
    this.appendLs(sbuf,"<th>日志产生时间</th>");
    this.appendLs(sbuf,"<th>线程名称</th>");
    this.appendLs(sbuf,"<th>日志级别</th>");
    this.appendLs(sbuf,"<th>日志名称</th>");
    if (this.locationInfo) {
      this.appendLs(sbuf,"<th>File:Line</th>");
    }

    this.appendLs(sbuf,"<th>消息体</th>");
    this.appendLs(sbuf,"</tr>");
    return sbuf.toString().getBytes(this.getCharset());
  }

  @Override
  public byte[] getFooter() {
    StringBuilder sbuf = new StringBuilder();
    this.appendLs(sbuf,"</table>");
    this.appendLs(sbuf,"<br>");
    this.appendLs(sbuf,"</body></html>");
    return this.getBytes(sbuf.toString());
  }

  @PluginFactory
  public static CustomHtmlLayout createLayout(@PluginAttribute("locationInfo") boolean
                                                        locationInfo,@PluginAttribute(
          value = "title",
          defaultString = "Log4j Log Messages") String title,@PluginAttribute("contentType")
          String contentType,@PluginAttribute(
          value = "charset",
          defaultString = "UTF-8") Charset charset,@PluginAttribute("fontSize") String fontSize,
                                              @PluginAttribute(
          value = "fontName",
          defaultString = "arial,sans-serif") String font) {
    HtmlLayout.FontSize fs = HtmlLayout.FontSize.getFontSize(fontSize);
    fontSize = fs.getFontSize();
    String headerSize = fs.larger().getFontSize();
    if (contentType == null) {
      contentType = "text/html; charset=" + charset;
    }

    return new CustomHtmlLayout(locationInfo,title,contentType,charset,font,fontSize,headerSize);
  }

  public static CustomHtmlLayout createDefaultLayout() {
    return newBuilder().build();
  }

  @PluginBuilderFactory
  public static CustomHtmlLayout.Builder newBuilder() {
    return new CustomHtmlLayout.Builder();
  }

  static {
    REGEXP = "\n".equals(Strings.LINE_SEPARATOR) ? "\n" : Strings.LINE_SEPARATOR + "|\n";
  }

  public static class Builder implements org.apache.logging.log4j.core.util
          .Builder<CustomHtmlLayout> {
    @PluginBuilderAttribute
    private boolean locationInfo;
    @PluginBuilderAttribute
    private String title;
    @PluginBuilderAttribute
    private String contentType;
    @PluginBuilderAttribute
    private Charset charset;
    @PluginBuilderAttribute
    private CustomHtmlLayout.FontSize fontSize;
    @PluginBuilderAttribute
    private String fontName;

    private Builder() {
      this.locationInfo = false;
      this.title = "Log4j Log Messages";
      this.contentType = null;
      this.charset = StandardCharsets.UTF_8;
      this.fontSize = CustomHtmlLayout.FontSize.SMALL;
      this.fontName = "arial,sans-serif";
    }

    public CustomHtmlLayout.Builder withLocationInfo(boolean locationInfo) {
      this.locationInfo = locationInfo;
      return this;
    }

    public CustomHtmlLayout.Builder withTitle(String title) {
      this.title = title;
      return this;
    }

    public CustomHtmlLayout.Builder withContentType(String contentType) {
      this.contentType = contentType;
      return this;
    }

    public CustomHtmlLayout.Builder withCharset(Charset charset) {
      this.charset = charset;
      return this;
    }

    public CustomHtmlLayout.Builder withFontSize(CustomHtmlLayout.FontSize fontSize) {
      this.fontSize = fontSize;
      return this;
    }

    public CustomHtmlLayout.Builder withFontName(String fontName) {
      this.fontName = fontName;
      return this;
    }

    @Override
    public CustomHtmlLayout build() {
      if (this.contentType == null) {
        this.contentType = "text/html; charset=" + this.charset;
      }

      return new CustomHtmlLayout(this.locationInfo,this.title,this.contentType,this.charset,this
              .fontName,this.fontSize.getFontSize(),this.fontSize.larger().getFontSize());
    }
  }

  public enum FontSize {
    /**smaller*/
    SMALLER("smaller"),
    /**xx-small*/
    XXSMALL("xx-small"),
    /**x-small*/
    XSMALL("x-small"),
    /**small*/
    SMALL("small"),
    /**medium*/
    MEDIUM("medium"),
    /***/
    LARGE("large"),
    /**large*/
    XLARGE("x-large"),
    /**xx-large*/
    XXLARGE("xx-large"),
    /**larger*/
    LARGER("larger");

    private final String size;

    FontSize(String size) {
      this.size = size;
    }

    public String getFontSize() {
      return this.size;
    }

    public static CustomHtmlLayout.FontSize getFontSize(String size) {
      CustomHtmlLayout.FontSize[] arr = values();
      int len = arr.length;

      for (int i = 0; i < len; ++i) {
        CustomHtmlLayout.FontSize fontSize = arr[i];
        if (fontSize.size.equals(size)) {
          return fontSize;
        }
      }

      return SMALL;
    }

    public CustomHtmlLayout.FontSize larger() {
      return this.ordinal() < XXLARGE.ordinal() ? values()[this.ordinal() + 1] : this;
    }
  }

}
