package cn.followtry.spring.cloud.followtrycloud;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jingzhongzhi
 * @Description
 * @since 2019/10/14
 */
@RequestMapping("/test")
@RestController
public class TestController {

    @RequestMapping("/config")
    public String test(){
        return "success";
    }
}