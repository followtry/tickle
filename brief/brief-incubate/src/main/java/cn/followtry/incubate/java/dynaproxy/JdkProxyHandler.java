/**
 * 
 */
package cn.followtry.incubate.java.dynaproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author jingzz
 * @time 2016年11月15日 下午2:17:22
 * @name brief-example-temp/cn.followtry.prac.java.dynaproxy.My
 * @since 2016年11月15日 下午2:17:22
 */
public class JdkProxyHandler implements InvocationHandler{
	
	private Object proxied;
	
	public JdkProxyHandler(Object obj) {
		this.proxied = obj;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("JDK动态代理前置");
		Object invoke = method.invoke(proxied, args);
		System.out.println("JDK动态代理后置");
		return invoke;
	}
}
