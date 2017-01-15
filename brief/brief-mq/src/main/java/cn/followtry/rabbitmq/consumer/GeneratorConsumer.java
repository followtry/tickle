/**
 * 
 */
package cn.followtry.rabbitmq.consumer;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.ShutdownSignalException;

import cn.followtry.comm.util.TimeUtil;
import cn.followtry.rabbitmq.core.BaseConfig;

/**
 * @author jingzz
 * @time 2017年1月14日 上午12:41:30
 * @func
 * @name GeneratorProducer
 */
public class GeneratorConsumer {

	// 创建连接工厂
	private static ConnectionFactory connFac = new ConnectionFactory();

	public static void main(String[] args) throws IOException, TimeoutException, ShutdownSignalException,
			ConsumerCancelledException, InterruptedException {
		connFac.setConnectionTimeout(3000);
		connFac.setHost(BaseConfig.HOST);
		connFac.setVirtualHost(BaseConfig.VIRTUALHOST);
		// 创建连接
		Connection conn = connFac.newConnection();
		// 创建信道
		Channel channel = conn.createChannel();

		BuiltinExchangeType type = BuiltinExchangeType.TOPIC;
		// 声明交换器
		channel.exchangeDeclare(BaseConfig.EXCHANGE, type, BaseConfig.DURABLE);
		// 声明队列
		channel.queueDeclarePassive(BaseConfig.QUEUE);
		channel.queueBind(BaseConfig.QUEUE, BaseConfig.EXCHANGE, BaseConfig.ROUTINGKEY);

		QueueingConsumer consumer = new QueueingConsumer(channel);
		channel.basicConsume(BaseConfig.QUEUE, true, consumer);
			// nextDelivery是一个阻塞方法（内部实现其实是阻塞队列的take方法）
			QueueingConsumer.Delivery delivery = consumer.nextDelivery();
			String message = new String(delivery.getBody());
			System.out.println(TimeUtil.formatLocalDateTime(System.currentTimeMillis()) + "=" + message + "'");
	}
}
