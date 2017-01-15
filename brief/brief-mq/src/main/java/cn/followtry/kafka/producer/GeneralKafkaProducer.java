package cn.followtry.kafka.producer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.followtry.kafka.util.KafkaProperties;

/**
 * brief-kafka/cn.followtry.kafka.GeneralKafkaProducer
 * 
 * @author jingzz
 * @since 2016年12月29日 下午4:56:53
 */
public class GeneralKafkaProducer<K, V> {

	private static final Logger LOGGER = LoggerFactory.getLogger(GeneralKafkaProducer.class);

	/** kafka自带生产者处理类 */
	private KafkaProducer<K, V> kafkaProducer;

	/** 获取配置属性 */
	private Properties properties = KafkaProperties.getProducerProperties();

	private ProducerRecord<K, V> record;
	
	/** 构建消息数据结构 */
	private KafkaBean<K, V> kafkaBean;

	public GeneralKafkaProducer(KafkaBean<K, V> kafkaBean) {
		kafkaProducer = new KafkaProducer<>(properties);
		this.kafkaBean = kafkaBean;
		record = new ProducerRecord<>(kafkaBean.getTopic(), kafkaBean.getPartition(), kafkaBean.getKey(),
				kafkaBean.getValue());
	}

	public Future<RecordMetadata> send() throws InterruptedException, ExecutionException {
		return sendAsyncRecord(kafkaBean, null);
	}
	
	public Future<RecordMetadata> send(Callback callback) throws InterruptedException, ExecutionException {
		return sendAsyncRecord(null,callback);
	}
	
	public Future<RecordMetadata> send(KafkaBean<K, V> kafkaBean,Callback callback) throws InterruptedException, ExecutionException {
		return sendAsyncRecord(kafkaBean,callback);
	}
	
	public Future<RecordMetadata> send(KafkaBean<K, V> kafkaBean) throws InterruptedException, ExecutionException {
		return sendAsyncRecord(kafkaBean, null);
	}

	/**
	 *  阻塞方式异步发送消息
	 * @author jingzz
	 * @param kafkaBean
	 * @return
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	public RecordMetadata sendBlock(KafkaBean<K, V> kafkaBean) throws InterruptedException, ExecutionException {
		return sendRecord(kafkaBean, null);
	}
	
	/**
	 * 模拟阻塞方式发送消息，并获取到返回的RecordMetadata信息
	 * @author jingzz
	 * @param kafkaBean2 
	 * @param callback
	 * @return 
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	private RecordMetadata sendRecord(KafkaBean<K, V> kafkaBean, Callback callback) throws InterruptedException, ExecutionException {
		if (kafkaBean != null) {
			setProducerRecord(kafkaBean);
		}
		return kafkaProducer.send(record, callback).get();
	}
	
	private Future<RecordMetadata> sendAsyncRecord(KafkaBean<K, V> kafkaBean, Callback callback) throws InterruptedException, ExecutionException {
		if (kafkaBean != null) {
			setProducerRecord(kafkaBean);
		}
		return kafkaProducer.send(record, callback);
	}
	
	/**
	 * @author jingzz
	 * @param kafkaBean
	 */
	private void setProducerRecord(KafkaBean<K, V> kafkaBean) {
		LOGGER.info("消息内容为:{}", kafkaBean.toString());
		record = new ProducerRecord<>(kafkaBean.getTopic(), kafkaBean.getPartition(), kafkaBean.getKey(),
				kafkaBean.getValue());
	}
	
	
	public void close(){
		if (kafkaProducer != null) {
			kafkaProducer.close();
		}
		this.kafkaProducer = null;
	}

}
