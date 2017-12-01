package cn.followtry.design.pattern.observer;

/**
 * 具体发布者
 * Created by followtry on 2017/6/5.
 */
public class MathTeacher extends AbstractTopic{
  
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
    final StringBuilder sb = new StringBuilder("MathTeacher{");
    sb.append("instruction='").append(instruction).append('\'');
    sb.append('}');
    return sb.toString();
  }
}
