package cn.followtry.kafka;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

import cn.followtry.kafka.consumer.GeneralKafkaConsumer;
import cn.followtry.kafka.executor.MsgBody;

/**
 * brief-kafka/cn.followtry.kafka.GeneralKafkaProducerTest
 * 
 * @author jingzz
 * @since 2016年12月29日 下午5:19:13
 */
public class GeneralKafkaConsumerTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(GeneralKafkaConsumerTest.class);

	@SuppressWarnings("unused")
	private static long offset = 0;

	public static void main(String[] args)
			throws InterruptedException, ExecutionException, Exception, IllegalAccessException, ClassNotFoundException {
		GeneralKafkaConsumer<String, String> consumer = new GeneralKafkaConsumer<String, String>(Arrays.asList("test"));
		while (true) {
			int num = 0;
			ConsumerRecords<String, String> records = consumer.poll();
			if (records != null && records.count() > 0) {
				for (ConsumerRecord<String, String> record : records) {
					LOGGER.info("--{}--:主题={},分区={},主键={},值={},偏移量={}", ++num, record.topic(), record.partition(),
							record.key(), record.value(), record.offset());
					offset = record.offset();
					try {
						executor(record);
					} catch (Exception e) {
						LOGGER.error("执行异常：",e);
					}
				}
			}

			TimeUnit.SECONDS.sleep(3);
		}
	}

	/**
	 * @author jingzz
	 * @param record
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 */
	private static void executor(ConsumerRecord<String, String> record) throws InstantiationException,
			IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException {
		MsgBody msgBody = JSON.parseObject(record.value(), MsgBody.class);
		Object target = Class.forName(msgBody.getType()).newInstance();
		Method method = target.getClass().getMethod(msgBody.getMethodName(), msgBody.getArgsType());
		if (msgBody.getArgsType() == null && msgBody.getArgsValue() == null) {
			//无操作，直接往下执行
		}else if ((msgBody.getArgsType() == null && msgBody.getArgsValue() != null)
				|| (msgBody.getArgsType() != null && msgBody.getArgsValue() == null)
				|| (msgBody.getArgsType().length != msgBody.getArgsValue().length)) {
			throw new IllegalArgumentException("参数与值的数量不匹配");
		}
		method.invoke(target, msgBody.getArgsValue());
	}
}
