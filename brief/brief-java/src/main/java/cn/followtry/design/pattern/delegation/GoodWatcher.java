package cn.followtry.design.pattern.delegation;

/**
 * 放哨人(前台妹子)
 * Created by followtry on 2017/6/5.
 */
public class GoodWatcher extends AbstractNotifier {
  
  private String name;
  
  public GoodWatcher(String name) {
    this.name = name;
  }
  
  @Override
  public Event addListenter(String targetName,Object target,String methodName,Object... params) {
    sendResponse(targetName);
    return this.getEventHandler().addEvent(target,methodName,params);
  }
  
  @Override
  public void sendNotify() {
    //发送短信，邮件等异构消息
    System.out.println("情况有变，我这个负责的哨兵要马上通知我的委托者们！");
    this.getEventHandler().sendNotify();
  }
  
  private void sendResponse(String targetName){
    //可以发送短信，邮件等异构消息方式通知订阅者
    System.out.println(targetName+"你好，我是负责的前台["+name+"]，情况有变时我会及时通知你！");
  }
}
