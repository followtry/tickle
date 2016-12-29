package cn.followtry.kafka.producer;

import java.io.Serializable;

/**
 *  brief-kafka/cn.followtry.kafka.KafkaBean
 * @author 
 *		jingzz 
 * @since 
 *		2016年12月29日 下午4:51:59
 */
public class KafkaBean<K,V> implements Serializable {
	
	private static final long serialVersionUID = -3409575197826506815L;

	/** 消息所属主题 */
	private String topic;
	
	/** 消息所属分区  */
	private Integer partition;
	
	/** 消息的key */
	private K key;
	
	/** 消息的value */
	private V value;
	
	public KafkaBean(String topic,Integer partition,K key ,V value){
		this.topic = topic;
		this.partition = partition;
		this.key = key;
		this.value = value;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public Integer getPartition() {
		return partition;
	}

	public void setPartition(Integer partition) {
		this.partition = partition;
	}

	public K getKey() {
		return key;
	}

	public void setKey(K key) {
		this.key = key;
	}

	public V getValue() {
		return value;
	}

	public void setValue(V value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "KafkaBean [topic=" + topic + ", partition=" + partition + ", key=" + key + ", value=" + value + "]";
	}
	
}
