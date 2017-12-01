package cn.followtry.design.pattern.observer;

import lombok.Getter;
import lombok.Setter;

/**
 * 观察者(订阅者)抽象类
 * Created by followtry on 2017/6/5.
 */
@Getter
@Setter
public abstract class AbstractObServer {
  
  private String name;
  
  private Topic topic;
  
  public AbstractObServer(String name, Topic topic) {
    this.name = name;
    this.topic = topic;
  }
  
  abstract void accepet(String msg);
}
