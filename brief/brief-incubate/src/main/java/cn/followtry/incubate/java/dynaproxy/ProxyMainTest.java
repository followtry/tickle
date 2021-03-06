/**
 * 
 */
package cn.followtry.incubate.java.dynaproxy;

/**
 * 实战练习JDK的接口动态代理和cglib的字节码动态代理。
 * @author jingzz
 * @time 2016年11月15日 下午3:35:29
 * @name brief-example-temp/cn.followtry.prac.java.dynaproxy.ProxyMainTest
 * @since 2016年11月15日 下午3:35:29
 */
public class ProxyMainTest {
	public static void main(String[] args) {
		jdkproxy();
		cglibProxy();
	}

	/**
	 * 测试使用cglib动态生成代理类
	 * @author jingzz
	 */
	private static void cglibProxy() {
		CGlibProxy cGlibProxy = new CGlibProxy();
		HelloService proxy = cGlibProxy.getProxy(HelloServiceImpl.class);
		proxy.say("hello world");
	}

	/**
	 * 测试使用JDK动态生成的代理类
	 * @author jingzz
	 */
	private static void jdkproxy() {
		HelloService m = new HelloServiceImpl();
		JdkProxyFactoryBean proxyFactoryBean = new JdkProxyFactoryBean();
		HelloService newInstan = proxyFactoryBean.getNewInstan(m);
		newInstan.say("hello world");
	}
}
