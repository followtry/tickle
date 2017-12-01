package cn.followtry.incubate.java.dynaproxy.aop;

import org.aspectj.lang.annotation.Before;

/**
 * Created by followtry on 2017/3/26 0026.
 */
public class UserServiceImpl implements UserService {


    @Override
    @Before("")
    public void sayHello(User user) {
        System.out.println(user);
    }
}
