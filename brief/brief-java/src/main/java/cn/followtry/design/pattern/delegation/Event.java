package cn.followtry.design.pattern.delegation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 事件包装器，封装转换所有的具体实体类对象
 * Created by followtry on 2017/6/5.
 */
public class Event {
  
  //目标对象
  private Object target;
  
  //目标方法
  private String methodName;
  
  //目标方法参数
  private Object[] params;
  
  //目标方法参数类型
  private Class[] paramTypes;
  
  public Event(Object target,String methodName,Object[] params) {
    this.target = target;
    this.methodName = methodName;
    this.params = params;
    extractType(params);
    
  }
  
  private void extractType(Object[] params) {
    if (params != null && params.length > 0) {
      this.paramTypes = new Class[params.length];
      for (int i = 0; i < params.length; i++) {
        this.paramTypes[i] = params[i].getClass();
      }
      
    }
  }
  
  public Object invoke() throws NoSuchMethodException, InvocationTargetException,
          IllegalAccessException {
    Method method = target.getClass().getDeclaredMethod(methodName,paramTypes);
    if (method != null) {
      return method.invoke(target,params);
    }
    throw new NoSuchMethodException(methodName + "方法不存在");
  }
  
  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Event{");
    sb.append("target=").append(target);
    sb.append(", methodName='").append(methodName).append('\'');
    sb.append(", params=").append(Arrays.toString(params));
    sb.append(", paramTypes=").append(Arrays.toString(paramTypes));
    sb.append('}');
    return sb.toString();
  }
}
