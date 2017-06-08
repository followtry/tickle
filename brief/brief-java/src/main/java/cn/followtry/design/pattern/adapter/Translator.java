package cn.followtry.design.pattern.adapter;

/**
 * Created by followtry on 17/6/7.
 */
public class Translator extends Player{
  
  String action;
  
  Player foregin;
  
  public Translator(String name,String position) {
    super(name,position);
  }
  
  @Override
  String action() {
    return action;
  }
  
  @Override
  Player action(String action) {
    if ("attack".equals(action)) {
      action = "进攻";
    } else if ("defense".equals(action)) {
      action = "后退";
    } else {
      action ="";
    }
    Player foreginPlayer = new ForeignPlayer(getName(),getPosition()).action(action);
    return foreginPlayer;
  }
}
