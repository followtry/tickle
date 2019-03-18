package cn.followtry.flink;

import avro.shaded.com.google.common.collect.ImmutableMap;
import org.apache.beam.runners.flink.FlinkRunner;
import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.kafka.KafkaIO;
import org.apache.beam.sdk.io.kafka.KafkaRecord;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.PTransform;
import org.apache.beam.sdk.values.PCollection;
import org.apache.beam.sdk.values.PDone;
import org.apache.kafka.common.serialization.StringDeserializer;

/**
 * @author jingzhongzhi
 * @Description
 * @since 2019-03-15
 */
public class BeamOnFlink {

    private static String bootstrapServers = "127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183";

    public static void main(String[] args) {
        // 创建管道工厂
        PipelineOptions options = PipelineOptionsFactory.create();
        // 显式指定 PipelineRunner：FlinkRunner 必须指定如果不制定则为本地
        options.setRunner(FlinkRunner.class);
        // 设置相关管道
        Pipeline pipeline = Pipeline.create(options);
        // 这里 kV 后说明 kafka 中的 key 和 value 均为 String 类型
        PCollection<KafkaRecord<String, String>> lines = pipeline.apply(KafkaIO.<String,String>read().withBootstrapServers(bootstrapServers)
                        .withTopic("beam-on-flink")// 必需设置要读取的 kafka 的 topic 名称
                        .withKeyDeserializer(StringDeserializer.class)// 必需序列化 key
                        .withValueDeserializer(StringDeserializer.class)// 必需序列化 value
                        .updateConsumerProperties(ImmutableMap.of("auto.offset.reset", "earliest"))
                        .withMaxNumRecords(5));// 这个属性 kafka 最常见的.
        // 为输出的消息类型。或者进行处理后返回的消息类型
//        PCollection<String> kafkadata = lines.apply("Remove Kafka Metadata", ParDo.of(new DoFn<KafkaRecord<String, String>, String>() {
//            private static final long serialVersionUID = 1L;
//
//            @ProcessElement
//            public void processElement(ProcessContext ctx) {
//                System.out.print(" 输出的分区为 ----：" + ctx.element().getKV());
//                ctx.output(ctx.element().getKV().getValue());// 其实我们这里是把 " 张海     涛在发送消息 ***" 进行返回操作
//            }
//        }));
//        PCollection<String> windowedEvents = kafkadata.apply(Window.<String>into(FixedWindows.of(Duration.standardSeconds(5))));
//        PCollection<KV<String, Long>> wordcount = windowedEvents.apply(Count.<String>perElement()); // 统计每一个 kafka 消息的 Count
//        PCollection<String> wordtj = wordcount.apply("ConcatResultKVs", MapElements.via( // 拼接最后的格式化输出（Key 为 Word，Value 为 Count）
//                new SimpleFunction<KV<String, Long>, String>() {
//                    private static final long serialVersionUID = 1L;
//
//                    @Override
//                    public String apply(KV<String, Long> input) {
//                        System.out.print(" 进行统计：" + input.getKey() + ": " + input.getValue());
//                        return input.getKey() + ": " + input.getValue();
//                    }
//                }));
//        wordtj.apply(KafkaIO.<Void, String>write().withBootstrapServers(bootstrapServers)// 设置写会 kafka 的集群配置地址
//                .withTopic("beam-on-flink-res")// 设置返回 kafka 的消息主题
//                // .withKeySerializer(StringSerializer.class)// 这里不用设置了，因为上面 Void
//                .withValueSerializer(StringSerializer.class)
//                // Dataflow runner and Spark 兼容， Flink 对 kafka0.11 才支持。我的版本是 0.10 不兼容
//                //.withEOS(20, "eos-sink-group-id")
//                .values() // 只需要在此写入默认的 key 就行了，默认为 null 值
//        ); // 输出结果
        KafkaIO.Write<String, String> write = KafkaIO.<String, String>write().withBootstrapServers(bootstrapServers).withTopic("beam-on-flink-res");
        PTransform<PCollection<String>, PDone> values = write.values();
        pipeline.apply("write_res", values);
        pipeline.run().waitUntilFinish();
    }
}
