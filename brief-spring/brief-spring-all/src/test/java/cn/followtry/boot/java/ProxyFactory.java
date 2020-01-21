package cn.followtry.boot.java;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author jingzhongzhi
 * @Description
 * @since 2020/1/20
 */
public class ProxyFactory implements InvocationHandler {

    private Object object;

    public ProxyFactory(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("cn.followtry.boot.java.test.ProxyFactory.invoke()");
        return method.invoke(object,args);
    }
}
