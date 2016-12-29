/**
 * 
 */
package cn.followtry.incubate.java.dynaproxy;

/**
 * @author jingzz
 * @time 2016年11月15日 下午2:14:18
 * @name brief-example-temp/cn.followtry.prac.java.dynaproxy.MyDynamicProxy
 * @since 2016年11月15日 下午2:14:18
 */
public class HelloServiceImpl implements HelloService{

	public String say(String content) {
		System.out.println("我要说的话是：" + content);
		return content;
	}
}
