package cn.followtry.design.pattern.command;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by followtry on 17/6/7.
 */
public class Waiter {
  private List<AbstractCommand> commandList = new ArrayList<>();
  
  public String acceptOrder(BarBecueKind barBecueKind,Integer num){
    BakeCommand bakeCommand;
    switch (barBecueKind) {
      case Mutton:
        bakeCommand = new BakeCommand(new BakeMuttonBarbecuer(),num);
        break;
      case ChickenWing:
        bakeCommand = new BakeCommand(new BakeChikenWingBarbecuer(),num);
        break;
      default:
        bakeCommand = null;
    }
    if (bakeCommand == null) {
      return "没有该食材";
    }
    if (commandList.add(bakeCommand)){
      return "收到您定的"+barBecueKind.getName()+" "+num+"个";
    }
    return "订单太多，请稍等！";
  }
  
  public String notifyCookHouse(){
    StringBuilder sb = new StringBuilder();
    commandList.forEach(command -> {
      sb.append(command.executeCommand()).append(";");
    });
    clear();
    return sb.toString();
  }
  
  private void clear() {
    commandList.clear();
  }
}
