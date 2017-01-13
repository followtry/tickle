/**
 * 
 */
package cn.jingzztech.prac.org.redis;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.followtry.incubate.org.redis.RedisService;

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
		RedisService service = (RedisService)context.getBean("cn.jingzztech.prac.redis.RedisService");
		String key = "name";
		/*service.setData(key, "jingzz");
		String data = (String)service.getData(key);
		System.out.println(data);*/
	}
}
