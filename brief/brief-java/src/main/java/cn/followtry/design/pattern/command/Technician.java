package cn.followtry.design.pattern.command;

/**
 * 烤串师傅
 * Created by followtry on 17/6/7.
 */
public interface Technician {
  
  String executeCommand(Integer newOrderNum);
  
//  String refuseCommand(Integer newOrderNum);
}
