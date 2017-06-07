package cn.followtry.design.pattern.adapter;

/**
 * 中锋
 * Created by followtry on 17/6/7.
 */
public class CenterPlayer extends Player{
  
  String action;
  
  public CenterPlayer(String name,String position) {
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
