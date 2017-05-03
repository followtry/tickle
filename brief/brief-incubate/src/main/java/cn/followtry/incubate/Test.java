package cn.followtry.incubate;

import com.alibaba.fastjson.JSON;

/**
 * Created by followtry on 2017/4/26. ${END}
 */
public class Test {
  static String str = "{\"msg\":\"添加人员【吴梦姣】（0000073575）信息的返回结果\",\"stackInfo\":\"{\\\"code\\\":20009," +
          "\\\"level\\\":0,\\\"msg\\\":\\\"此用户已停用\\\"," +
          "\\\"data\\\":{\\\"memberId\\\":\\\"1355\\\"}}\"}";

  /** main. */
  public static void main(String[] args) {
    Object parse = JSON.parse(str);
    System.out.println(parse);
  }
}
