/**
 * 
 */
package cn.jingzztech.prac.mybatis;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.jingzztech.prac.mybatis.bean.UserBean;

/**
 * @author jingzz
 * @time 2016年2月22日 上午10:27:11
 * @func 
 * @name MybatisTest
 */
public class MybatisTest {
	private static ApplicationContext context;
	static {
		context = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
	}
	public static void main(String[] args) {
		UserService service = context.getBean(UserService.class);
		List<UserBean> users = service.selectAll();
		System.out.println(users);
	}
}
