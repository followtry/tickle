package cn.followtry.design.pattern.responsibilitychain;

/**
 * Created by followtry on 17/6/8.
 */
public abstract class Manager {
  String name;
  
  public Manager(String name,Manager superior) {
    this.name = name;
    this.superior = superior;
  }
  
  //上级
  protected Manager superior;
  
  abstract void request(Request request);
}
