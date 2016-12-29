/**
 * 
 */
package cn.followtry.incubate.java.dynaproxy;

import java.lang.reflect.Proxy;

/**
 * 初学动态代理,
 * 试用泛型动态判断对象类型
 * 
 * <p>
 * 为动态代理封装方法
 * @author jingzz
 * @time 2016年11月15日 下午2:31:16
 * @name brief-example-temp/cn.followtry.prac.java.dynaproxy.ProxyFactoryBean
 * @since 2016年11月15日 下午2:31:16
 */
public class JdkProxyFactoryBean {

	@SuppressWarnings("unchecked")
	public <T> T getNewInstan(T obj) {
		T proxyInstance = (T) Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(),
				new JdkProxyHandler(obj));
		return proxyInstance;
	}

}
