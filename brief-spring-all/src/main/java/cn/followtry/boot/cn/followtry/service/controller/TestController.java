package cn.followtry.boot.cn.followtry.service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jingzhongzhi
 * @Description
 * @since 2019/10/15
 */
@RestController
public class TestController {

    @RequestMapping("/")
    public Object getTest(){
        return "success";
    }
}
