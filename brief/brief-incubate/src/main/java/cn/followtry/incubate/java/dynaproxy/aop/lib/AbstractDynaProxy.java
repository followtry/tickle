package cn.followtry.incubate.java.dynaproxy.aop.lib;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Before;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * JDK的动态dialing。
 * JDK产生代理所花时间少，但是执行效率低
 * Created by followtry on 2017/3/26 0026.
 */
public abstract class AbstractDynaProxy implements InvocationHandler {

    private Object target;

    public AbstractDynaProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {

        Method m = method;
        Object invoke;
        if (hasAnnotation(m, Around.class)) {
            invoke = aroundInvoke(target, method, objects);
        } else {
            if (hasAnnotation(m, Before.class)) {

                beforeInvoke(target, method, objects);
            }

            invoke = doInvoke(target,method,objects);
            if (hasAnnotation(m, After.class)) {
                afterInvoke(target, method, objects);
            }
        }
        return invoke;
    }

    private <T extends Annotation> Boolean hasAnnotation(Method m, Class<T> anno) {
       return m.isAnnotationPresent(anno);
    }

    protected abstract void beforeInvoke(Object o, Method method, Object[] objects);

    protected abstract void afterInvoke(Object o, Method method, Object[] objects);

    /**
     * 环绕aop
     *
     * @param o
     * @param method
     * @param objects
     * @return
     * @throws Throwable
     */
    protected Object aroundInvoke(Object o, Method method, Object[] objects) throws Throwable {
        beforeInvoke(target, method, objects);
        Object invoke = doInvoke(target, method, objects);
        afterInvoke(target, method, objects);
        return invoke;
    }

    public Object doInvoke(Object target, Method method, Object[] objects) throws InvocationTargetException, IllegalAccessException {
        return method.invoke(target, objects);
    }
}
