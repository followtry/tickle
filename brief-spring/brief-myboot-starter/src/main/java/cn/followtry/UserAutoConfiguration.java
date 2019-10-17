package cn.followtry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author jingzhongzhi
 * @Description
 * @since 2019/10/17
 */
@Configuration
@ConditionalOnClass(User.class)
@EnableConfigurationProperties(MyBootUserProperties.class)
public class UserAutoConfiguration implements InitializingBean {

    private static final Logger log = LoggerFactory.getLogger(UserAutoConfiguration.class);

    @Bean
    @ConditionalOnMissingBean(User.class)
    public User getUserInfo(MyBootUserProperties properties){
        log.info("my-boot.properties: {}",properties);
        User user = new UserInfo(1,properties.getName(),properties.getAge(),properties.getSex(),properties.getLocation());
        return user;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("======初始化自定义的配置:{}",this.getClass().getCanonicalName());
    }
}
