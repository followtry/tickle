package cn.followtry.springboot;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cn.followtry.validation.base.DefaultAspectServiceChain;
import cn.followtry.validation.base.logger.LoggingAspect;
import cn.followtry.validation.http.MonitorServlet;

/**
 *  brief-springboot/cn.followtry.springboot.ConfIns
 * @author 
 *		jingzz 
 * @since 
 *		2017年1月12日 下午12:50:14
 */
@Configuration
public class ConfIns {
	
	@Bean
	public LoggingAspect getLoggingAspect(){
		LoggingAspect loggingAspect = new LoggingAspect();
		return loggingAspect;
	}
	
	@Bean
	public DefaultAspectServiceChain getDefaultAspectServiceChain(){
		DefaultAspectServiceChain defaultAspectServiceChain = new DefaultAspectServiceChain();
		return defaultAspectServiceChain;
	}
}
