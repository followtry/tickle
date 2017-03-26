package cn.followtry.incubate.java.dynaproxy.aop;

import org.aspectj.lang.annotation.Around;

/**
 * Created by followtry on 2017/3/26 0026.
 */
public interface UserService {

    @Around("")
    void sayHello(User user);
}
