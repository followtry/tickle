package cn.followtry.boot.java.service;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

/**
 * @author jingzhongzhi
 * @Description
 * @since 2019/12/3
 */
@Service("Cat")
@Order(10)
public class CatHelloService implements HelloService {
    @Override
    public void sayHello() {
        System.out.println("I'm a Cat,Hello world!");
    }
}
