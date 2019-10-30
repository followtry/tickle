package cn.followtry.boot.java.service;


import cn.followtry.scanner.MyAnno;

/**
 * @author jingzhongzhi
 * @Description
 * @since 2019/10/29
 */
@MyAnno(value = "my_first_test_scanner_service")
public class MyService {

    public String hello() {
        return "hello myService";
    }
}
