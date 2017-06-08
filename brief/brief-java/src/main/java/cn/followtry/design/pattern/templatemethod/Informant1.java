package cn.followtry.design.pattern.templatemethod;

/**
 * Created by followtry on 2017/6/6.
 */
public class Informant1 extends Questionnaire {
  
  @Override
  String name() {
    return "张三";
  }
  
  @Override
  String sex() {
    return "男";
  }
  
  @Override
  String age() {
    return "22";
  }
  
  @Override
  String jobs() {
    return "程序员";
  }
  
  @Override
  String addr() {
    return "北京";
  }
}
