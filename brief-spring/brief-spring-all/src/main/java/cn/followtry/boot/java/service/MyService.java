package cn.followtry.boot.java.service;


//import cn.followtry.scanner.MyAnno;

import org.springframework.stereotype.Component;

/**
 * @author jingzhongzhi
 * @Description
 * @since 2019/10/29
 */
//@MyAnno(value = "my_first_test_scanner_service")
@Component
public class MyService {

    public MyService() {
        System.out.println("实例化MyService");
    }

    public String hello() {
        return "hello myService";
    }
}
