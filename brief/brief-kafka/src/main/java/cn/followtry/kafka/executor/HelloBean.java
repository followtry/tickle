package cn.followtry.kafka.executor;

/**
 * brief-kafka/cn.followtry.kafka.executor.HelloBean
 * 
 * @author jingzz
 * @since 2016年12月29日 下午7:42:30
 */
public class HelloBean {

	public void sayHello(String name, Integer age) {
		System.out.println("你好，" + age + "岁的" + name);
	}
	
	public void sayHello() {
		System.out.println("你好，欢迎访问fakfa");
	}
}
