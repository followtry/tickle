package cn.followtry.design.pattern.adapter;

/**
 * 前锋
 * Created by followtry on 17/6/7.
 */
public class ForwardPlayer extends Player{
  
  String action;
  
  public ForwardPlayer(String name,String position) {
    super(name,position);
  }
  
  String action() {
    return action;
  }
  
  public Player action(String action) {
    this.action = action;
    return this;
  }
}
