/**
 * 
 */
package cn.followtry.rabbitmq.core;

/**
 * @author jingzz
 * @time 2017年1月14日 下午12:22:33
 * @func 
 * @name BaseConfig
 */
public interface BaseConfig {
	
	String QUEUE = "my-test";
	String EXCHANGE = "jingzz_test";
	String ROUTINGKEY = "jingzz";
	String HOST = "localhost";
	String VIRTUALHOST = "jingzz-rabbitmq";

	Boolean DURABLE =false;

	String ADMINPWD= "jingzz";
	String ADMIN= "jingzz";
	int PORT=5672;
}
