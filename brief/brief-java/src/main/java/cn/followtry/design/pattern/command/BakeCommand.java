package cn.followtry.design.pattern.command;

/**
 * 烧烤的命令
 * Created by followtry on 17/6/7.
 */
public class BakeCommand extends AbstractCommand {
  
  private Integer newOrderNum;
  
  public BakeCommand(Technician technician,Integer orderNum) {
    super(technician);
    this.newOrderNum = orderNum;
  }
  
  @Override
  String executeCommand() {
      return technician.executeCommand(newOrderNum);
  }
  
  @Override
  String refuseCommand() {
//    return technician.refuseCommand(newOrderNum);
    return null;
  }
}
