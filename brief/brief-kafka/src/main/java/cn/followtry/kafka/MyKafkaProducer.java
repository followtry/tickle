package cn.followtry.kafka;

import java.util.Properties;
import java.util.Random;
import java.util.PrimitiveIterator.OfDouble;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.ByteArraySerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * brief-kafka/cn.followtry.kafka.KafkaProducer
 * 
 * @author jingzz
 * @since 2016年12月27日 下午8:16:50
 */
public class MyKafkaProducer {

	private static final Logger LOGGER = LoggerFactory.getLogger(MyKafkaProducer.class);

	private String topic;

	// 主机名只能是ip地址
	private static final String BOOTSTART_SERVER = "192.168.2.201:9092,192.168.2.202:9093,192.168.2.203:9094";

	private static Random random = new Random();

	private Producer producer;

	/**
	 * @author jingzz
	 * @return
	 */
	public static Properties getProperties() {
		Properties properties = new Properties();
		properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTART_SERVER);
		properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		properties.put(ProducerConfig.ACKS_CONFIG, "all");
		properties.put(ProducerConfig.RETRIES_CONFIG, 1);
		properties.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
		properties.put(ProducerConfig.LINGER_MS_CONFIG, 1);
		properties.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);

		return properties;
	}

	public static void main(String[] args) throws InterruptedException, ExecutionException {

		Producer<String, String> producer = new KafkaProducer<>(getProperties());
		for (int i = 0; i < 100; i++) {

			Future<RecordMetadata> send = producer
					.send(new ProducerRecord<String, String>("test", Integer.toString(i), Integer.toString(i)));

			LOGGER.info("{},发送消息的元数据为：topic={},partition={},offset={}", i, send.get().topic(), send.get().partition(),
					send.get().offset());
			TimeUnit.SECONDS.sleep(1);
		}
		producer.close();
		System.out.println("主线程结束");

	}
}
