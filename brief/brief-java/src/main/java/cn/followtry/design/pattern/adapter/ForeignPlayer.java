package cn.followtry.design.pattern.adapter;

/**
 * 外国球员
 * Created by followtry on 17/6/7.
 */
public class ForeignPlayer extends AbstractPlayer {
  
  String action;
  
  public ForeignPlayer(String name,String position) {
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
