package cn.followtry.boot.java;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;

/**
 * @author jingzhongzhi
 * @Description
 * @since 2020/1/20
 */
public class RefMain{

    /**
     * main.
     */
    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        TestA instance = (TestA)Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{TestA.class}, new ProxyFactory(new TestA() {
            @Override
            public Object getB() {
                return 20;
            }
        }));
        System.out.println(instance.getA());
        System.out.println("结束");
    }
}
