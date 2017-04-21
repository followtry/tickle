package cn.followtry.incubate.java.dynaproxy.aop.lib;

/**
 * Created by followtry on 2017/4/13.
 */
public interface CglibProxy {

	<T> T getProxy(Class<T> clazz);

}
