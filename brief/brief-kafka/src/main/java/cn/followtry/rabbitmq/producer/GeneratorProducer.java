/**
 * 
 */
package cn.followtry.rabbitmq.producer;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author jingzz
 * @time 2017年1月14日 上午12:41:30
 * @func
 * @name GeneratorProducer
 */
public class GeneratorProducer {

	// 创建连接工厂
	private static ConnectionFactory connFac = new ConnectionFactory();
	private static String queue = "my-test2";
	private static String exchange = "hello-exchange";
	private static String routingKey = "hola";

	public static void main(String[] args) throws IOException, TimeoutException {
		connFac.setConnectionTimeout(3000);
		connFac.setHost("localhost");
		// 创建连接
		Connection conn = connFac.newConnection();
		// 创建信道
		Channel channel = conn.createChannel();

		boolean durable = false;
		BuiltinExchangeType type = BuiltinExchangeType.TOPIC;
		//声明交换器
		channel.exchangeDeclare(exchange, type, durable);
		//声明队列
		channel.queueDeclarePassive(queue);
		channel.queueBind(queue, exchange, routingKey);
		int times = 10;
		for (int i = 0; i < times ; i++) {
			//创建消息
			channel.basicPublish(exchange, routingKey, null ,"hello world".getBytes());
			System.out.println("发送消息成功:");
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		channel.close();
		conn.close();
	}
}
