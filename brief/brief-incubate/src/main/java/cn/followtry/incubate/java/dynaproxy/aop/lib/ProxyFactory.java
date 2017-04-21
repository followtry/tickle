package cn.followtry.incubate.java.dynaproxy.aop.lib;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;

/**
 * 代理工厂，封装对Proxy.newProxyInstance调用并进行强制转型
 * Created by followtry on 2017/3/26 0026.
 */
public class ProxyFactory {

	/**
	 * 对动态代理创建对象进行封装
	 *
	 * @param target       目标类，一般为接口实现类
	 * @param dynacHandler 实现InvocationHandler的类
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws InstantiationException
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getProxyInstance(Class<T> target, Class<? extends InvocationHandler> dynacHandler) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
		if (dynacHandler != null) {
			T res;
			if (target.isInterface()) {
				throw new InstantiationException("can not instantiate interface [" + target.getCanonicalName() + "]");
			} else {
				Constructor<? extends InvocationHandler> constructor=dynacHandler.getConstructor(Object.class);
				InvocationHandler handler=constructor.newInstance(target.newInstance());
				res=(T) Proxy.newProxyInstance(target.getClassLoader(), target.getInterfaces(), handler);
			}
			return res;
		}
		return null;
	}

	public static <T> T getCglibProxyInstance(Class<T> target, Class<? extends AbstractCglibProxy> cglibHandler) throws InstantiationException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		if (target != null) {
			if (target.isInterface()) {
				throw new InstantiationException("can not instantiate interface [" + target.getCanonicalName() + "]");
			}else{
				Constructor<? extends AbstractCglibProxy> constructor=cglibHandler.getConstructor();
				constructor.setAccessible(true);
				CglibProxy cglibProxy=constructor.newInstance();
				return cglibProxy.getProxy(target);
			}
		}
		throw new NullPointerException("target class can not be empty");
	}
}
