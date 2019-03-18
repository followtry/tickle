package cn.followtry.flink;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.Count;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.MapElements;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.transforms.SimpleFunction;
import org.apache.beam.sdk.values.KV;

/**
 * 在Beam上直接运行代码
 * @author jingzhongzhi
 * @Description
 * @since 2019-03-18
 */
public class DirectBeamTest {
    /**
     * main.
     */
    public static void main(String[] args) {

        String basePath = "/Users/jingzhongzhi/tmp/";
        String inFilePath = basePath + "beam.log";
        String outFilePath = basePath + "beam-flink-res.log";
        PipelineOptions pipelineOptions = PipelineOptionsFactory.create();
//        pipelineOptions.setRunner(DirectRunner.class);
//        pipelineOptions.setRunner(FlinkRunner.class);
        pipelineOptions.setJobName("direct-beam-test-followtry");
        Pipeline p = Pipeline.create(pipelineOptions);
        p.apply("real_file",TextIO.read().from(inFilePath))
            .apply("extract_eord", ParDo.of(new DoFn<String, String>() {

                @ProcessElement
                public void processElement(ProcessContext c){
                    for (String word : c.element().split(" ")) {
                        if (!word.isEmpty()){
                            System.out.println("读取文件中的数据: " + word);
                            c.output(word);
                        }
                    }
                }
            }))
                .apply("count", Count.perElement())
                .apply("format_result", MapElements.via(new SimpleFunction<KV<String, Long>, String>() {
                    @Override
                    public String apply(KV<String, Long> input) {
                        return input.getKey() + ":" + input.getValue();
                    }
                }))
                .apply("write_out_file",TextIO.write().to(outFilePath));

        p.run().waitUntilFinish();


    }
}
