package cn.followtry.boot.java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author jingzhongzhi
 */
@SpringBootApplication
@ComponentScan(basePackages = "cn.followtry.boot.java")
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
@EnableWebMvc
public class BriefSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(BriefSpringbootApplication.class);
        application.run(args);
    }

}
