package cn.followtry.design.pattern.responsibilitychain;

/**
 * Created by followtry on 17/6/8.
 */
public abstract class AbstractManager {
  String name;
  
  public AbstractManager(String name, AbstractManager superior) {
    this.name = name;
    this.superior = superior;
  }
  
  //上级
  protected AbstractManager superior;
  
  abstract void request(Request request);
}
