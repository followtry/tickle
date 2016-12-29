/**
 * 
 */
package cn.followtry.incubate.springinaction;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

import cn.followtry.incubate.springinaction.inject.runtime.ConfigInject;
import cn.followtry.incubate.springinaction.inject.runtime.ConfigInject2;
import cn.followtry.incubate.springinaction.inject.runtime.InjectComponent;
import cn.followtry.incubate.springinaction.inject.runtime.InjectComponent2;

/**
 * 启动spring的方式
 * 
 * @author jingzz
 * @time 2016年9月18日 上午9:34:19
 * @name brief-service/cn.jingzz.brief.springinaction.SpringMainContext
 * @since 2016年9月18日 上午9:34:19
 */
public class SpringInjectValueContext {

	public static void main(String[] args) {
		boolean useplaceHolder = true;
		
		if (useplaceHolder) {
			placeHolderInjectValue();
		}else {
			envInjectValue();
		}
		

	}

	/**
	 * 通过显式通过java代码方式注入bean，根据@{@link Configuration}注入实例
	 * 
	 * @author jingzz
	 */
	@SuppressWarnings("resource")
	protected static void envInjectValue() {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConfigInject.class);
		InjectComponent bean = context.getBean(InjectComponent.class);
		bean.play();
		InjectComponent2 bean2 = context.getBean(InjectComponent2.class);
		bean2.play();
	}
	
	@SuppressWarnings("resource")
	protected static void placeHolderInjectValue() {
		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConfigInject2.class);
		InjectComponent bean = context.getBean(InjectComponent.class);
		bean.play();
		InjectComponent2 bean2 = context.getBean(InjectComponent2.class);
		bean2.play();
	}
	
	
}
