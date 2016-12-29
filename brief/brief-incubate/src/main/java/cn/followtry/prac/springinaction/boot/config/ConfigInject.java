/**
 * 
 */
package cn.followtry.prac.springinaction.boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author jingzz
 * @time 2016年9月19日 上午10:51:39
 * @name brief-example/cn.jingzztech.prac.springinaction.boot.config.ConfigInject
 * @since 2016年9月19日 上午10:51:39
 */
@Configuration
public class ConfigInject {
	
	@Bean(name="configConfigComponent")
	public ConfigComponent annotationComponent(){
		return new ConfigComponent();
	}
	
	@Bean(name="configConfigComponent2")
	public ConfigComponent2 annotationComponent2(){
		return new ConfigComponent2();
	}
}
