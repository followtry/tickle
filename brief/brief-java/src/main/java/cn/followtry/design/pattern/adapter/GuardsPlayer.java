package cn.followtry.design.pattern.adapter;

/**
 * 后卫
 * Created by followtry on 17/6/7.
 */
public class GuardsPlayer extends Player{
  
  String action;
  
  public GuardsPlayer(String name,String position) {
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
