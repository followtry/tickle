package cn.followtry.design.pattern.delegation;

import com.google.common.collect.Lists;
import java.util.List;

/**
 * 事件处理类，解耦观察者和主题接口，使得事件通知更通用
 * Created by followtry on 2017/6/5.
 */
public class EventHandler {
  
  List<Event> eventList = Lists.newArrayList();
  
  public Event addEvent(Object target,String methodName,Object... params) {
    Event event = new Event(target,methodName,params);
    if (event != null) {
      eventList.add(event);
      return event;
    }
    return null;
  }
  
  public void sendNotify() {
    eventList.forEach(event -> {
      try {
        event.invoke();
      } catch (Exception e) {
        System.out.println("         这个委托者有问题，没设置正确" + event+"\n"+e);
      }
    });
  }
}
