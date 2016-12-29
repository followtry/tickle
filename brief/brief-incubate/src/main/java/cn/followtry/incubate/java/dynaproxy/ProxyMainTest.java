/**
 * 
 */
package cn.followtry.incubate.java.dynaproxy;

/**
 * @author jingzz
 * @time 2016年11月15日 下午3:35:29
 * @name brief-example-temp/cn.followtry.prac.java.dynaproxy.ProxyMainTest
 * @since 2016年11月15日 下午3:35:29
 */
public class ProxyMainTest {
	public static void main(String[] args) {
		JDKProxy();
		cglibProxy();
	}

	/**
	 * 测试使用cglib动态生成代理类
	 * @author jingzz
	 */
	private static void cglibProxy() {
		CGlibProxy cGlibProxy = new CGlibProxy();
		HelloServiceImpl proxy = (HelloServiceImpl)cGlibProxy.getProxy(HelloServiceImpl.class);
		proxy.say("hello world");
	}

	/**
	 * 测试使用JDK动态生成的代理类
	 * @author jingzz
	 */
	private static void JDKProxy() {
		HelloService m = new HelloServiceImpl();
		JdkProxyFactoryBean proxyFactoryBean = new JdkProxyFactoryBean();
		HelloService newInstan = proxyFactoryBean.getNewInstan(m);
		newInstan.say("hello world");
	}
}
