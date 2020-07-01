package cn.followtry.boot.java.service;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * @author jingzhongzhi
 * @Description
 * @since 2020/1/17
 */
@Configuration
@ConditionalOnBean(MyService.class)
@Import(DogHelloService.class)
public class Config {

    @Autowired
    @Qualifier(value = "pigHelloService")
    private HelloService pigHelloService;

    @Autowired
    private ConfigurableEnvironment configurableEnvironment;

    @Bean
    public HelloService getDonkeyHelloService() {
        HelloService donkeyHelloService = new HelloService() {
            @Override
            public void sayHello() {
                System.out.println("I'm a donkey,Hello world!");
            }
        };
        return donkeyHelloService;
    }

    @PostConstruct
    private void init() {
        String[] activeProfiles = configurableEnvironment.getActiveProfiles();
        System.out.println("active profiles " + JSON.toJSONString(activeProfiles));
        Map<String, Object> systemProperties = configurableEnvironment.getSystemProperties();
        for (Map.Entry<String, Object> entry : systemProperties.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }

        System.out.println("=============config hr===========");
        Map<String, Object> systemEnvironment = configurableEnvironment.getSystemEnvironment();
        for (Map.Entry<String, Object> entry : systemEnvironment.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
    }

}
