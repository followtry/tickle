package cn.followtry.design.pattern.adapter;

/**
 * 外国球员
 * Created by followtry on 17/6/7.
 */
public class ForeignPlayer extends Player{
  
  String action;
  
  public ForeignPlayer(String name,String position) {
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
