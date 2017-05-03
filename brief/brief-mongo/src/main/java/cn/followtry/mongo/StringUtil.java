package cn.followtry.mongo;

/**
 * 处理日志字符串 Created by followtry on 2017/4/26. ${END}
 */
public class StringUtil {

  private static final String PLACE_HOLER = "{}";

  private static final String EMPTY_CHAR = "";

  public static String getLoggerMsg(String format,Object... args) {
    if (format != null) {
      StringBuilder sb = new StringBuilder();
      if (args == null || args.length == 0) {
        return format.replace(PLACE_HOLER,"");
      }
      int index = 0;
      int argsLen = args.length;
      for (int i = 0; i < argsLen; i++) {
        if (index > format.length()) {
          break;
        }
        int endIndex = format.indexOf(PLACE_HOLER,index);
        if (endIndex >= 0) {
          sb.append(format.substring(index,endIndex));
          //正常情况下，将{}替换为指定参数
          index = endIndex <= format.length() - 2 ?endIndex + 2:endIndex;
          sb.append(args[i]);

          //最后一个参数
          if (i == argsLen - 1) {//参数少，占位符多
            sb.append(format.substring(index).replace(PLACE_HOLER,EMPTY_CHAR));
            break;
          }
        }else {//参数多，占位符少
          String subStr = format.substring(index);
          sb.append(subStr.replace(PLACE_HOLER,EMPTY_CHAR));
          break;
        }
      }
      return sb.toString().replace(PLACE_HOLER,EMPTY_CHAR);
    }
    return null;
  }
}
