package cn.followtry.design.pattern.command;

/**
 * 命令接口
 * Created by followtry on 17/6/7.
 */
public abstract class AbstractCommand {
  
  //执行命令的技师
  protected Technician technician;
  
  public AbstractCommand(Technician technician) {
    this.technician = technician;
  }
  
  //执行命令
  abstract String executeCommand();
  
  //拒绝执行命令
  abstract String refuseCommand();
}
