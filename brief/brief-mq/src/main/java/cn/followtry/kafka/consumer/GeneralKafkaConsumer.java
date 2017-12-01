package cn.followtry.kafka.consumer;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.commons.collections.MapUtils;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.clients.consumer.OffsetCommitCallback;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.followtry.kafka.util.KafkaProperties;

/**
 * brief-kafka/cn.followtry.kafka.consumer.GeneralKafkaConsumer
 * 
 * @author jingzz
 * @since 2016年12月29日 下午6:29:28
 */
public class GeneralKafkaConsumer<K, V> {

	private static final Logger LOGGER = LoggerFactory.getLogger(GeneralKafkaConsumer.class);

	private KafkaConsumer<K, V> consumer;

	private Properties properties = KafkaProperties.getConsumerProperties();

	private static final long DEFAULT_TIME_OUT = 1000;

	private long timeout = DEFAULT_TIME_OUT;

	public GeneralKafkaConsumer(List<String> topics) {
		consumer = new KafkaConsumer<K, V>(properties);
		consumer.subscribe(topics);
	}

	public ConsumerRecords<K, V> poll() {
		ConsumerRecords<K, V> records = consumer.poll(timeout);
		LOGGER.info("获取到的消息总数为{}个", records.count());
		consumer.commitAsync(new OffsetCommitCallback() {


            @Override
			public void onComplete(Map<TopicPartition, OffsetAndMetadata> offsets, Exception exception) {
				if (MapUtils.isNotEmpty(offsets)) {
					for (Entry<TopicPartition, OffsetAndMetadata> entry : offsets.entrySet()) {
						System.out.println("key=" + entry.getKey() + ",value=" + entry.getValue());
					}
				}
			}
		});
		return records;
	}

}
