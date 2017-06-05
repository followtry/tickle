package cn.followtry.design.pattern.observer;

/**
 * 抽象主题接口
 * Created by followtry on 2017/6/5.
 */
public interface Topic {
  
  void attach(ObServer obServer);
  
  void detach(ObServer obServer);

  void sendNotify();
  
}
