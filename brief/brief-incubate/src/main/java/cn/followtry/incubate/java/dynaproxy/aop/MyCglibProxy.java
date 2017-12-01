package cn.followtry.incubate.java.dynaproxy.aop;

import cn.followtry.incubate.java.dynaproxy.aop.lib.AbstractCglibProxy;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by followtry on 2017/4/13.
 */
public class MyCglibProxy extends AbstractCglibProxy{


    @Override
	protected void beforeAdvice(Object object, Method method, Object[] params, MethodProxy proxy) {
		System.out.println("cglib动态代理前置w");
	}


    @Override
	protected void afterAdvice(Object object, Method method, Object[] params, MethodProxy proxy) {
		System.out.println("cglib动态代理后置c");
	}
}
