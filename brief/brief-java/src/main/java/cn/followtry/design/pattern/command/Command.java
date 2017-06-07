package cn.followtry.design.pattern.command;

/**
 * 命令接口
 * Created by followtry on 17/6/7.
 */
public abstract class Command {
  
  //执行命令的技师
  protected Technician technician;
  
  public Command(Technician technician) {
    this.technician = technician;
  }
  
  //执行命令
  abstract String executeCommand();
  
  //拒绝执行命令
  abstract String refuseCommand();
}
