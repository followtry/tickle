/**
 * 
 */
package cn.followtry.incubate.java.dynaproxy;


import java.lang.reflect.Method;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

/**
 * 初学cglib动态代理
 * @author jingzz
 * @time 2016年11月15日 下午3:37:43
 * @name brief-example-temp/cn.followtry.prac.java.dynaproxy.CGlibProxy
 * @since 2016年11月15日 下午3:37:43
 */
public class CGlibProxy implements MethodInterceptor {
	
	private Enhancer enhancer = new Enhancer();
	
	@SuppressWarnings({"rawtypes" })
	public Object getProxy(Class clazz){
		enhancer.setSuperclass(clazz);
		enhancer.setCallback(this);
		return enhancer.create();
	}

	@Override
	public Object intercept(Object object, Method method, Object[] params, MethodProxy proxy) throws Throwable {
		System.out.println("cglib动态代理前置");
		Object invokeSuper = proxy.invokeSuper(object, params);
		System.out.println("cglib动态代理后置");
		return invokeSuper;
	}


	

}
