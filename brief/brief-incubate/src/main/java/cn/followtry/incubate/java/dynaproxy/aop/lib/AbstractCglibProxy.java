/**
 * 
 */
package cn.followtry.incubate.java.dynaproxy.aop.lib;


import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 设计cglib动态代理
 */
public abstract class AbstractCglibProxy implements MethodInterceptor,CglibProxy {
	
	private Enhancer enhancer = new Enhancer();
	
	@SuppressWarnings({"rawtypes" })
	@Override
	public <T> T getProxy(Class<T> clazz){
		enhancer.setSuperclass(clazz);
		enhancer.setCallback(this);
		return (T) enhancer.create();
	}

	/**
	 * implemented by subclass
	 */
	protected void beforeAdvice(Object object, Method method, Object[] params, MethodProxy proxy){}

	/**
	 * implemented by subclass
	 */
	protected void afterAdvice(Object object, Method method, Object[] params, MethodProxy proxy){}


	protected Object aroundAdvice(Object object, Method method, Object[] params, MethodProxy proxy) throws Throwable {
		beforeAdvice(object,method,params,proxy);
		Object invokeSuper = doIntercept(object,params,proxy);
		afterAdvice(object,method,params,proxy);
		return invokeSuper;
	}

	@Override
	public Object intercept(Object object, Method method, Object[] params, MethodProxy proxy) throws Throwable {
		return aroundAdvice(object,method,params,proxy);
	}

	private Object doIntercept(Object object, Object[] params, MethodProxy proxy) throws Throwable {
		return proxy.invokeSuper(object, params);
	}


}
