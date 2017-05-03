package cn.followtry.logger;

import com.alibaba.fastjson.JSON;
import java.util.Map;

/**
 * 日志内容消息体
 *
 * <p>Created by followtry on 2017/4/25. ${END}
 */
public class LoggerBody {
  private Object msg;

  private Object detailInfo;

  private Map<String,Object> params;

  private Throwable throwable;

  public static LoggerBody getObj(){
    return new LoggerBody();
  }

  public Map<String,Object> getParams() {
    return params;
  }
  public LoggerBody setParams(Map<String,Object> params) {
    this.params = params;
    return this;
  }

  public Object getMsg() {
    return msg;
  }

  public LoggerBody setMsg(Object msg) {
    this.msg = msg;
    return this;
  }

  public Object getDetailInfo() {
    return detailInfo;
  }

  public LoggerBody setDetailInfo(Object detailInfo) {
    this.detailInfo = detailInfo;
    return this;
  }

  public Throwable getThrowable() {
    return throwable;
  }

  public LoggerBody setThrowable(Throwable throwable) {
    this.throwable = throwable;
    return this;
  }

  @Override
  public String toString() {
    return JSON.toJSONString(this);
  }

  public String toJsonString() {
    return JSON.toJSONString(this);
  }

  /**
   * 获取日志消息体
   * @param format 自定义格式化串，带有{}占位符的格式
   * @param args 替换占位符的参数
   * @return
   */
  public static String getLoggerMsg(String format,Object... args) {
    return getObj().setMsg(StringUtil.getLoggerMsg(format,args)).toJsonString();
  }
}
