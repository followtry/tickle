package cn.followtry.boot.java.service;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

/**
 * @author jingzhongzhi
 * @Description
 * @since 2019/12/3
 */
public class TestMain {
    /**
     * main.
     */
    public static void main(String[] args) {
        GenericApplicationContext applicationContext = new AnnotationConfigApplicationContext("cn.followtry.boot");
        TestService testService = applicationContext.getBean(TestService.class);
        testService.sayHello();
        System.out.println("结束");
    }
}
