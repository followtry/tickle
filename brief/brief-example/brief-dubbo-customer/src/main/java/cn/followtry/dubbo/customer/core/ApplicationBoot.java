/**
 * 
 */
package cn.followtry.dubbo.customer.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.followtry.dubbo.bean.User;
import cn.followtry.dubbo.customer.ServiceHandler;

/**
 * @author jingzz
 * @time 2016年10月26日 下午4:16:03
 * @name brief-dubbo-customer/cn.followtry.dubbo.customer.core.ApplicationBoot
 * @since 2016年10月26日 下午4:16:03
 */
public class ApplicationBoot {
	
	private static ApplicationContext context;
	
	static {
		context = new ClassPathXmlApplicationContext("classpath*:applicationContext.xml");
	}
	
	public static void main(String[] args) {
		ServiceHandler handler = context.getBean(ServiceHandler.class);
		User user = handler.getUser("2345");
		System.out.println(user);
	}
}
