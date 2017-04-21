package cn.followtry.springboot;

import cn.followtry.validation.base.DefaultAspectServiceChain;
import cn.followtry.validation.base.logger.LoggingAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 实例化.
 *
 * @author jingzz
 * @since 2017年1月12日 下午12:50:14
 */
@Configuration
public class ConfIns {

  @Bean
  public LoggingAspect getLoggingAspect() {
    LoggingAspect loggingAspect = new LoggingAspect();
    return loggingAspect;
  }

  @Bean
  public DefaultAspectServiceChain getDefaultAspectServiceChain() {
    DefaultAspectServiceChain defaultAspectServiceChain = new DefaultAspectServiceChain();
    return defaultAspectServiceChain;
  }
}
