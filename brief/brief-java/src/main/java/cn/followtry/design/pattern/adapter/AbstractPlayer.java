package cn.followtry.design.pattern.adapter;

import lombok.Getter;

/**
 * Created by followtry on 17/6/7.
 */
@Getter
public abstract class AbstractPlayer {
  
  String name;
  
  String position;
  
  
  public AbstractPlayer(String name, String position) {
    this.name = name;
    this.position = position;
  }
  
  abstract String action();
  
  abstract AbstractPlayer action(String action);
  
  void attack() {
    command();
  }
  
  private void command() {
    System.out.println(position+"["+name+"]"+action());
  }
  
  void defense() {
    command();
  }
}
