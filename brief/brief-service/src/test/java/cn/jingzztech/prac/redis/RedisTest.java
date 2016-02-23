/**
 * 
 */
package cn.jingzztech.prac.redis;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author jingzz
 * @time 2016年2月23日 下午3:00:35
 * @func 
 * @name RedisTest
 */
public class RedisTest {
	private static ApplicationContext context;
	static {
		context = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
	}
	public static void main(String[] args) {
		RedisService service = context.getBean(RedisService.class);
		String key = "name";
		service.setData(key, "jingzz");
		String data = (String)service.getData(key);
		System.out.println(data);
	}
}
