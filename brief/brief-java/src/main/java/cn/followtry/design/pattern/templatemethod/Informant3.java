package cn.followtry.design.pattern.templatemethod;

/**
 * Created by followtry on 2017/6/6.
 */
public class Informant3 extends Questionnaire {
  
  @Override
  String name() {
    return "王五";
  }
  
  @Override
  String sex() {
    return "男";
  }
  
  @Override
  String age() {
    return "31";
  }
  
  @Override
  String jobs() {
    return "经理";
  }
  
  @Override
  String addr() {
    return "南京";
  }
}
