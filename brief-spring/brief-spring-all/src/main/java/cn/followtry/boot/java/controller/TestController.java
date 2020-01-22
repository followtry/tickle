package cn.followtry.boot.java.controller;

import cn.followtry.boot.java.service.ApplicationService;
import cn.followtry.boot.java.service.MyService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.InitializingBean;
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
@RequestMapping("java")
public class TestController implements InitializingBean {

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private MyService myService;

    @RequestMapping("/")
    public Object getTest() {
        return "success";
    }

    @RequestMapping(value = "/application", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getApplication() {
        return JSON.toJSONString(applicationService.getBeanDefinitionNames());
    }

    @RequestMapping(value = "/service", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getData() {
        String hello = myService.hello();
        return JSON.toJSONString(hello);
    }

    @RequestMapping(value = "/getUser", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getUserInfo(Long id) {
        return applicationService.getUser(id);
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("打印TestController InitializingBean 调用信息");
    }
}
