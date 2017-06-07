package cn.followtry.incubate.java.dynaproxy.aop;

import cn.followtry.incubate.java.dynaproxy.aop.lib.ProxyFactory;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by followtry on 2017/3/26 0026.
 */
public class JdkAopTest {

    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        User user = new User();
        user.setName("jingzz");
        user.setId("ddddddddddoo");
        UserService proxyInstance = ProxyFactory.getProxyInstance(UserServiceImpl.class, MyDynaProxy.class);
        proxyInstance.sayHello(user);

        UserService cglibProxyInstance=ProxyFactory.getCglibProxyInstance(UserServiceImpl.class, MyCglibProxy.class);

        cglibProxyInstance.sayHello(user);
    }
}
