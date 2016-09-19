/**
 * 
 */
package cn.jingzz.brief.springinaction.boot;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * 启动spring的方式
 * 
 * @author jingzz
 * @time 2016年9月18日 上午9:34:19
 * @name brief-service/cn.jingzz.brief.springinaction.SpringMainContext
 * @since 2016年9月18日 上午9:34:19
 */
public class SpringMainContext {

	public static void main(String[] args) {
		annotationBootSpring();
	}

	/**
	 * 该方式通过引入注解的类获取spring的上下文，如果该类中有@{@link ComponentScan}
	 * 注解，会根据该注解的value扫描指定base-package下的注解了的类
	 * 
	 * @author jingzz
	 */
	protected static void annotationBootSpring() {
		@SuppressWarnings("resource")
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AnnotationComponent.class);
		AnnotationComponent bean = context.getBean(AnnotationComponent.class);
		bean.play();
		AnnotationComponent2 bean2 = context.getBean(AnnotationComponent2.class);
		bean2.play();
	}
}
