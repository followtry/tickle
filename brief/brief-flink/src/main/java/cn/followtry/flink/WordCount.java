package cn.followtry.flink;

import com.alibaba.fastjson.JSON;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.WindowedStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer011;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer011;
import org.apache.flink.util.Collector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * @author jingzhongzhi
 * @since 2019-01-13
 */
public class WordCount {

    private static final Logger log = LoggerFactory.getLogger(WordCount.class);

    private static String topic1 = "beam-on-flink";
    private static String topic2 = "beam-on-flink-res";

    /**
     * main.
     */
    public static void main(String[] args) throws Exception {

        /**
         * 1. 启动zk
         * 2. 启动kafka
         * 3. 在topic=test中写数据，
         * 4. 在topic=new_data_test中读数据
         */

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

//        DataStream<String> dateSource = env.socketTextStream("localhost", 9999).uid("1. data source");
        Properties properties = new Properties();
        String brokerServerList = "localhost:2181";
        properties.setProperty("bootstrap.servers", brokerServerList);
        properties.setProperty("group.id", topic1);
        FlinkKafkaConsumer011<String> flinkKafkaConsumer011 = new FlinkKafkaConsumer011<>("test", new SimpleStringSchema(), properties);

        DataStreamSource<String> source = env.addSource(flinkKafkaConsumer011);
        DataStream<WC> flatMap = source.flatMap(new Splitter()).uid("2. split data");
        KeyedStream<WC, Tuple> keyBy = flatMap.keyBy(0);
        WindowedStream<WC, Tuple, TimeWindow> window = keyBy.timeWindow(Time.seconds(10));
        DataStream<WC> dataStream = window.sum(1).uid("3. sum");

        DataStream<String> dateStreamRes = dataStream.map(WC::toString);
        //sink 到 kafka中
        sink2Kafka(brokerServerList, topic2, dateStreamRes);

//        tableSink.emitDataStream(dateStreamRes);
        System.out.println("=========================");
        env.execute("Window WordCount");

    }

    private static void sink2Kafka(String brokerServerList, String secondTopic, DataStream<String> dateStreamRes) {
        FlinkKafkaProducer011<String> sink2Kafka = new FlinkKafkaProducer011<>(brokerServerList,secondTopic, new SimpleStringSchema());
        dateStreamRes.addSink(sink2Kafka);
    }

    public static class Splitter implements FlatMapFunction<String, WC> {
        @Override
        public void flatMap(String sentence, Collector<WC> out) {
            for (String word: sentence.split(" ")) {
                out.collect(new WC(word, 1));
                log.info("word:{},count:{}",word,1);
            }
        }

    }

    /**
     * 将Tuple 替换为 Pojo对象
     */
    public static class WC extends Tuple2<String,Integer> {

        /**  */
        private String word;

        public WC() {
            super();
        }

        public WC(String word, Integer count) {
            super(word, count);
            this.word = word;
        }

        public String getWord() {
            return getField(0);
        }

        public void setWord(String word) {
            this.word = word;
            setField(word,0);
        }

        public String toJsonString() {
            return JSON.toJSONString(this);
        }


        @Override
        public String toString() {
            return "WC{" +
                    "f0=" + f0 +
                    ", f1=" + f1 +
                    '}';
        }
    }
}
