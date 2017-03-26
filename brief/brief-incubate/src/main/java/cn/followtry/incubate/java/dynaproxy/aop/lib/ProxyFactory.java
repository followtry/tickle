package cn.followtry.incubate.java.dynaproxy.aop.lib;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;

/**
 * Created by followtry on 2017/3/26 0026.
 */
public class ProxyFactory {

    public <T> T getProxyInstance(T target, Class<? extends InvocationHandler> dynacHandler) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        if (dynacHandler != null ) {
            Constructor<? extends InvocationHandler> constructor = dynacHandler.getConstructor(Object.class);
            InvocationHandler handler = constructor.newInstance(target);
            T res = (T) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), handler);
            return res;
        }
        return null;
    }
}
