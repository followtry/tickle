/**
 * 
 */
package cn.jingzztech.prac.org.mybatis;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author jingzz
 * @time 2016年2月22日 上午10:27:11
 * @func 
 * @name MybatisTest
 */
public class MybatisTest {
	private static ApplicationContext context;
	static {
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
	}
	public static void main(String[] args) {
		/*UserService service = context.getBean(UserService.class);
		TestTable record = new TestTable(12, "测试功能", new Date(), null);
		int insert = service.insert(record );
		System.out.println("插入成功条数:"+insert);*/
	}
}
