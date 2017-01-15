package cn.followtry.kafka;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;

import com.alibaba.fastjson.JSON;

import cn.followtry.kafka.executor.HelloBean;
import cn.followtry.kafka.executor.MsgBody;
import cn.followtry.kafka.producer.GeneralKafkaProducer;
import cn.followtry.kafka.producer.KafkaBean;

/**
 * brief-kafka/cn.followtry.kafka.GeneralKafkaProducerTest
 * 
 * @author jingzz
 * @since 2016年12月29日 下午5:19:13
 */
public class GeneralKafkaProducerTest {

	private static Random randomUtil = new Random();

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		KafkaBean<String, String> kafkaBean = new KafkaBean<String, String>("test", 0, "name",
				"myvalue " + randomUtil.nextInt());
		GeneralKafkaProducer<String, String> producer = new GeneralKafkaProducer<String, String>(kafkaBean);
		producer.send();
		MsgBody msgBody = new MsgBody();
		msgBody.setType(HelloBean.class.getName());
		msgBody.setMethodName("cn.followtry.kafka.executor.HelloBean.sayHello");
		/*
		 * 可以通过对参数序列和参数值序列的设置来调用sayHello的不同重载方法
		 */
		// msgBody.setArgsType(String.class, Integer.class);
		int times = 10;
		int i = 0;
		while (i < times) {
			// msgBody.setArgsValue("荆中志", randomUtil.nextInt(30));
			String value = JSON.toJSONString(msgBody);
			kafkaBean = new KafkaBean<String, String>("test", 0, "quartzwww", value);
			producer.send(kafkaBean, new Callback() {

				@Override
				public void onCompletion(RecordMetadata metadata, Exception exception) {
					System.out.println(metadata.offset() + "," + metadata.topic() + "," + metadata.partition());
				}
			});
			TimeUnit.SECONDS.sleep(1);
			i++;
		}
		producer.close();
	}

}
