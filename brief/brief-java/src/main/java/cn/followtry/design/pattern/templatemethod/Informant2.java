package cn.followtry.design.pattern.templatemethod;

/**
 * Created by followtry on 2017/6/6.
 */
public class Informant2 extends AbstractQuestionnaire {
  
  
  @Override
  String name() {
    return "李四";
  }
  
  
  @Override
  String sex() {
    return "女";
  }
  
  
  @Override
  String age() {
    return "24";
  }
  
  
  @Override
  String jobs() {
    return "HR";
  }
  
  
  @Override
  String addr() {
    return "天津";
  }
}
