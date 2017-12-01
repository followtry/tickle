package cn.followtry.design.pattern.adapter;

/**
 * 后卫
 * Created by followtry on 17/6/7.
 */
public class GuardsPlayer extends AbstractPlayer {
  
  String action;
  
  public GuardsPlayer(String name,String position) {
    super(name,position);
  }
  

  @Override
  String action() {
    return action;
  }
  

  @Override
  public AbstractPlayer action(String action) {
    this.action = action;
    return this;
  }
}
