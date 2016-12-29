package cn.followtry.kafka.util;

import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

/**
 *  brief-kafka/cn.followtry.kafka.util.KafkaProperties
 * @author 
 *		jingzz 
 * @since 
 *		2016年12月29日 下午4:59:42
 */
public class KafkaProperties {
	
	// 主机名只能是ip地址
	private static final String BOOTSTART_SERVER = "192.168.2.201:9092,192.168.2.202:9093,192.168.2.203:9094";
	
	private KafkaProperties(){}

	private static final String ACKS_VALUE = "all";
	
	private static Properties producerProperties;
	
	private static Properties consumerProperties;

	private static String ZK_SERVERS = "h2m1:2181,h2s1:2181,h2s2:2181";
	
	private static final Object PRODUCER_LOCK = new Object();
	
	private static final Object CONSUMER_LOCK = new Object();
	
	public static Properties getProducerProperties() {
		if (producerProperties == null) {
			synchronized (PRODUCER_LOCK) {
				if (producerProperties == null) {
					producerProperties = new Properties();
					producerProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTART_SERVER);
					producerProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
					producerProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
					producerProperties.put(ProducerConfig.ACKS_CONFIG, ACKS_VALUE);
					producerProperties.put(ProducerConfig.RETRIES_CONFIG, 1);
					producerProperties.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
					producerProperties.put(ProducerConfig.LINGER_MS_CONFIG, 1);
					producerProperties.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
				}
			}
		}

		return producerProperties;
	}
	
	public static Properties getConsumerProperties(){
		if (consumerProperties == null) {
			synchronized (CONSUMER_LOCK) {
				if (consumerProperties == null) {
					consumerProperties = new Properties();
					consumerProperties.put("zk.zk.connect", ZK_SERVERS );
					consumerProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTART_SERVER);
					consumerProperties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
					consumerProperties.put(ConsumerConfig.GROUP_ID_CONFIG, "test");
					consumerProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
					consumerProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
					consumerProperties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, 1000);
					consumerProperties.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, 30000);
					consumerProperties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, OffsetType.LATEST.getName());
				}
			}
		}
		return consumerProperties;
	}
	
	enum OffsetType{
		LATEST("latest"),
		EARLIEST("earliest"),
		NONE("none");
		
		private String name;
		
		private OffsetType(String name) {
			this.name = name;
		}
		
		public String getName(){
			return this.name.toLowerCase();
		}
	}

}
