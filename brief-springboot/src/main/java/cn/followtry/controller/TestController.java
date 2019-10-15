package cn.followtry.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jingzhongzhi
 * @Description
 * @since 2019/10/15
 */
@RestController
@RequestMapping("test")
public class TestController {

    @RequestMapping("test")
    public Object test(){
        return "success";
    }
}
