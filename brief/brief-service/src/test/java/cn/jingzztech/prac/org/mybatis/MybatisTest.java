/**
 * 
 */
package cn.jingzztech.prac.org.mybatis;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.jingzztech.prac.org.mybatis.UserService;
import cn.jingzztech.prac.org.mybatis.bean.UserBean;

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
		selectAllUser();
		/*UserService service = context.getBean(UserService.class);
		TestTable record = new TestTable(12, "测试功能", new Date(), null);
		int insert = service.insert(record );
		System.out.println("插入成功条数:"+insert);*/
	}
	/**
	 * 
	 */
	private static void selectAllUser() {
		UserService service = (UserService)context.getBean("cn.jingzztech.prac.mybatis.UserService");
		List<UserBean> users = service.selectAll();
		System.out.println(users);
	}
}
