package cn.followtry.design.pattern.adapter;

/**
 * 中锋
 * Created by followtry on 17/6/7.
 */
public class CenterPlayer extends AbstractPlayer {
  
  String action;
  
  public CenterPlayer(String name,String position) {
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
