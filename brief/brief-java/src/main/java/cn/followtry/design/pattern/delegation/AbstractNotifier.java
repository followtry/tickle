package cn.followtry.design.pattern.delegation;

/**
 * 放哨人
 * Created by followtry on 2017/6/5.
 */
public abstract class AbstractNotifier {
  
  private EventHandler eventHandler = new EventHandler();
  
  
  //添加监听者，也就是所有需要被通知的人
  abstract Event addListenter(String id,Object target,String methodName,Object[] params);
  
  public void setEventHandler(EventHandler handler){
    this.eventHandler = handler;
  }
  /**
   * 通知所有人
   */
  abstract void sendNotify();
  
  public EventHandler getEventHandler() {
    return eventHandler;
  }
}
