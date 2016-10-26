/**
 * 
 */
package cn.jingzztech.prac.springinaction.inject.runtime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * 使用系统属性Environment在运行时注入值
 * @author jingzz
 * @time 2016年9月19日 上午10:51:39
 * @name brief-example/cn.jingzztech.prac.springinaction.boot.config.ConfigInject
 * @since 2016年9月19日 上午10:51:39
 */
@Configuration
@PropertySource("classpath:/application.properties")
public class ConfigInject {
	
	@Autowired
	private Environment env;
	
	@Bean(name="configInjectComponent")
	public InjectComponent annotationComponent(){
		String name= null;
		if (env.containsProperty("test.name")) {
			name = env.getProperty("test.name");
		}
		int age = env.getProperty("test.age",Integer.class);
		boolean isMale = env.getProperty("test.isMale",Boolean.class);
		System.out.println("ConfigInject.annotationComponent()");
		if (isMale) {
			System.out.println(name+"是一个男孩");
		}else {
			System.out.println(name+"是一个女孩");
		}
		if (age > 0) {
			System.out.println(name+"的年龄是1:"+age);
		}else{
			System.out.println(name+"的年龄是2:"+age);
		}
		return new InjectComponent();
	}
	
	@Bean(name="configInjectComponent2")
	public InjectComponent2 annotationComponent2(){
		return new InjectComponent2();
	}
}
