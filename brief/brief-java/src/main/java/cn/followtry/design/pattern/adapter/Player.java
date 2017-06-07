package cn.followtry.design.pattern.adapter;

import lombok.Getter;

/**
 * Created by followtry on 17/6/7.
 */
@Getter
public abstract class Player {
  
  String name;
  
  String position;
  
  
  public Player(String name,String position) {
    this.name = name;
    this.position = position;
  }
  
  abstract String action();
  
  abstract Player action(String action);
  
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
