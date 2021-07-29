package cn.followtry.aop;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Advisor;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;

/**
 * @author followtry
 * @since 2021/7/29 11:38 上午
 */
@Configuration(proxyBeanMethods = false)
public class MyAopConfiguration {


    @Bean
    @ConditionalOnMissingBean(value = MyAopAdvisor.class)
    @Role(value = BeanDefinition.ROLE_INFRASTRUCTURE)
    public Advisor myAopAdvisor(MyAopAdvice advice) {

        MyAopAdvisor myAopAdvisor = new MyAopAdvisor();
        myAopAdvisor.setAdvice(advice);
        return myAopAdvisor;
    }

    @Bean
    @ConditionalOnMissingBean(value = MyAopAdvice.class)
    @Role(value = BeanDefinition.ROLE_INFRASTRUCTURE)
    public MyAopAdvice myAopAdvice() {
        return new MyAopAdvice();
    }


}
