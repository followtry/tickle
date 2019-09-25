package cn.followtry.flink;

import org.apache.beam.runners.flink.FlinkPipelineOptions;
import org.apache.beam.runners.flink.FlinkRunner;
import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.ParDo;

import java.time.Duration;

/**
 * @author jingzhongzhi
 * @Description
 * @since 2019-03-15
 */
public class BeamOnFlink {

    private static String bootstrapServers = "localhost:2181,localhost:2182,localhost:2183";

    private static String topic1 = "beam-on-flink";
    private static String topic2 = "beam-on-flink-res";

    private static String basePath = "/Users/jingzhongzhi/tmp/";
    private static String inFilePath = basePath + "beam.log";
    private static String outFilePath = basePath + "beam-flink-res.log";

    static final String TOKENIZER_PATTERN = "[^\\p{L}]+";

    public static void main(String[] args) {
        FlinkPipelineOptions options = PipelineOptionsFactory.as(FlinkPipelineOptions.class);
        options.setRunner(FlinkRunner.class);
        options.setAutoWatermarkInterval(Duration.ofSeconds(5).getSeconds());
        options.setEnableMetrics(true);
        options.setStreaming(true);
        // Create the Pipeline object with the options we defined above.
        Pipeline p = Pipeline.create(options);

//        p.apply(KafkaIO.<String, String>read()
//                .withBootstrapServers(bootstrapServers)
//                .withTopic(topic1)
//                .withKeyDeserializer(StringDeserializer.class)
//                .withValueDeserializer(StringDeserializer.class)
//
//                .updateConsumerProperties(ImmutableMap.of("auto.offset.reset", "earliest"))
//
//                // We're writing to a file, which does not support unbounded data sources. This line makes it bounded to
//                // the first 5 records.
//                // In reality, we would likely be writing to a data source that supports unbounded data, such as BigQuery.
//                .withMaxNumRecords(5)
//
//                .withoutMetadata() // PCollection<KV<Long, String>>
        p.apply("real_file", TextIO.read().from(inFilePath))
                .apply("ExtractWords", ParDo.of(new DoFn<String, String>() {
                    @ProcessElement
                    public void processElement(ProcessContext c) {
                        for (String word : c.element().split(TOKENIZER_PATTERN)) {
                            if (!word.isEmpty()) {
                                c.output(word);
                            }
                        }
                    }
                }));
//                .apply(Count.<String>perElement())
//                .apply("FormatResults", MapElements.via(new SimpleFunction<KV<String, Long>, String>() {
//                    @Override
//                    public String apply(KV<String, Long> input) {
//                        return input.getKey() + ": " + input.getValue();
//                    }
//                }))
//                .apply(TextIO.write().to(basePath + "wordcounts"));


        p.run(options).waitUntilFinish();
    }
}
