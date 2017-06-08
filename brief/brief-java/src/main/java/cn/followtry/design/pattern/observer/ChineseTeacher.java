package cn.followtry.design.pattern.observer;

/**
 * 具体发布者
 * Created by followtry on 2017/6/5.
 */
public class ChineseTeacher extends AbstractTopic{
  
  private String instruction;
  
  public String getInstruction() {
    return instruction;
  }
  
  public void setInstruction(String instruction) {
    this.instruction = instruction;
  }
  
  @Override
  String getMsg() {
    return getInstruction();
  }
  
  @Override
  public String toString() {
    return "ChineseTeacher{" + "instruction='" + instruction + '\'' + '}';
  }
}
