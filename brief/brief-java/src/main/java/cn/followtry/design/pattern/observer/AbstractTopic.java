package cn.followtry.design.pattern.observer;

import com.google.common.collect.Lists;
import java.util.List;

/**
 * 抽象的主题类
 *
 * <P>简单封装观察者方法，包括添加，接触和为这些观察者发送通知
 *
 * <P>Created by followtry on 2017/6/5.
 */
public abstract class AbstractTopic implements Topic{
  
  private List<ObServer> obServers = Lists.newArrayList();

  @Override
  public void attach(ObServer obServer) {
    obServers.add(obServer);
  }
  
  @Override
  public void detach(ObServer obServer) {
    obServers.remove(obServer);
  }
  
  abstract String getMsg();
  
  @Override
  public void sendNotify() {
    obServers.parallelStream().forEach(obServer -> obServer.accepet(getMsg()));
  }
}
