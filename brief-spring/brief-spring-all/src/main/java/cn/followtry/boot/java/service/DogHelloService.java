package cn.followtry.boot.java.service;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

/**
 * @author jingzhongzhi
 * @Description
 * @since 2019/12/3
 */
@Service
@Order(3)
public class DogHelloService implements HelloService {
    @Override
    public void sayHello() {
        System.out.println("I'm a Dog,Hello world!");
    }
}
