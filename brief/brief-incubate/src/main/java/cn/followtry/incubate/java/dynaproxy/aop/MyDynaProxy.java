package cn.followtry.incubate.java.dynaproxy.aop;

import cn.followtry.incubate.java.dynaproxy.aop.lib.AbstractDynaProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by followtry on 2017/3/26 0026.
 */
public class MyDynaProxy extends AbstractDynaProxy implements InvocationHandler {

    public MyDynaProxy(Object target) {
        super(target);
    }

    @Override
    public void beforeInvoke(Object o, Method method, Object[] objects) {
        System.out.println("before proxy");
    }

    @Override
    public void afterInvoke(Object o, Method method, Object[] objects) {
        System.out.println("after proxy");

    }
}
