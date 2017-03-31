/**
 * 
 */
package cn.followtry.rabbitmq.producer;

import cn.followtry.comm.util.TimeUtil;
import cn.followtry.rabbitmq.core.BaseConfig;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author jingzz
 * @time 2017年1月14日 上午12:41:30
 * @func
 * @name GeneratorProducer
 */
public class GeneratorProducer {

	// 创建连接工厂
	private static ConnectionFactory connFac = new ConnectionFactory();

	public static void main(String[] args) throws IOException, TimeoutException {
		connFac.setConnectionTimeout(3000);
		connFac.setHost(BaseConfig.HOST);
		connFac.setUsername(BaseConfig.ADMIN);
		connFac.setPassword(BaseConfig.ADMINPWD);
		connFac.setPort(BaseConfig.PORT);
		connFac.setVirtualHost(BaseConfig.VIRTUALHOST);
		// 创建连接
		Connection conn = connFac.newConnection();
		// 创建信道
		Channel channel = conn.createChannel();

		BuiltinExchangeType type = BuiltinExchangeType.TOPIC;
		// 声明交换器
		channel.exchangeDeclare(BaseConfig.EXCHANGE, type, BaseConfig.DURABLE);
		// 声明队列
		channel.queueDeclare(BaseConfig.QUEUE, BaseConfig.DURABLE, false, false, null);
		channel.queueBind(BaseConfig.QUEUE, BaseConfig.EXCHANGE, BaseConfig.ROUTINGKEY);
		int times = 1000;
		for (int i = 0; i < times; i++) {
			// 创建消息
			channel.basicPublish(BaseConfig.EXCHANGE, BaseConfig.ROUTINGKEY, null, "hello world".getBytes());
			System.out.println(TimeUtil.formatLocalDateTime(System.currentTimeMillis())+":发送消息成功:");
			try {
				TimeUnit.MILLISECONDS.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		channel.close();
		conn.close();
	}
}
