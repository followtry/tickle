package cn.followtry.boot.java;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author jingzhongzhi
 */
@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
@EnableWebMvc
@MapperScan("cn.followtry.boot.java.mybatis")
@EnableTransactionManagement(proxyTargetClass = true)
public class BriefSpringbootApplication {
    
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(BriefSpringbootApplication.class);
        application.run(args);
    }
    
}
