/**
 * 
 */
package cn.followtry.prac.springinaction.inject.runtime;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * @author jingzz
 * @time 2016年9月19日 上午10:51:39
 * @name brief-example/cn.jingzztech.prac.springinaction.boot.config.ConfigInject
 * @since 2016年9月19日 上午10:51:39
 */
@Configuration
public class ConfigInject2 {
	
	@Bean
	public PropertySourcesPlaceholderConfigurer placeholderConfigurerSupport(){
		PropertySourcesPlaceholderConfigurer placeholderConfigurer = new PropertySourcesPlaceholderConfigurer();
		String path = "application.properties";
		Resource classPathResource = new ClassPathResource(path );
		placeholderConfigurer.setLocation(classPathResource);
		return placeholderConfigurer;
	}
	
	@Bean(name="configInjectComponent")
	//条件注解，在指定条件满足情况下，实例化注解的bean
//	@Conditional(value = { PlaceHolderCondition.class })
	public InjectComponent annotationComponent(){
		System.out.println("ConfigInject.annotationComponent()");
		return new InjectComponent();
	}
	
	@Bean(name="configInjectComponent2")
	public InjectComponent2 annotationComponent2(){
		return new InjectComponent2();
	}
}
