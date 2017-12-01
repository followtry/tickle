package cn.followtry.design.pattern.adapter;

/**
 * Created by followtry on 17/6/7.
 */
public class Translator extends AbstractPlayer {
  
  String action;
  
  AbstractPlayer foregin;
  
  public Translator(String name,String position) {
    super(name,position);
  }
  

  @Override
  String action() {
    return action;
  }
  

  @Override
  AbstractPlayer action(String action) {
    if ("attack".equals(action)) {
      action = "进攻";
    } else if ("defense".equals(action)) {
      action = "后退";
    } else {
      action ="";
    }
    AbstractPlayer foreginPlayer = new ForeignPlayer(getName(),getPosition()).action(action);
    return foreginPlayer;
  }
}
