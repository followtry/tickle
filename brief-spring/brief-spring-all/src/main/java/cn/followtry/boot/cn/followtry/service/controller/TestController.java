package cn.followtry.boot.cn.followtry.service.controller;

import cn.followtry.User;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jingzhongzhi
 * @Description
 * @since 2019/10/15
 */
@RestController
public class TestController {

    @Autowired
    private User user;

    @RequestMapping("/")
    public Object getTest(){
        return "success";
    }

    @RequestMapping(value = "/user",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getUser(){
        return JSON.toJSONString(user.userInfo());
    }
}
