/**
 * 
 */
package cn.followtry.hadoop.demo.v1.mr;

import java.io.IOException;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

/**
 * @author jingzz
 * @time 2016年12月13日 下午6:46:06
 * @name brief-hadoop-demo/cn.followtry.hadoop.demo.mr.WordCountMapper
 * @since 2016年12月13日 下午6:46:06
 */
public class WordCountMapperV1 extends MapReduceBase implements Mapper<LongWritable, Text, Text, IntWritable>{

	private static final IntWritable ONE = new IntWritable(1);

	@Override
	public void map(LongWritable key, Text value, OutputCollector<Text, IntWritable> output, Reporter reporter)
			throws IOException {
		String line = value.toString();
		if (StringUtils.isNotEmpty(line)) {
			String[] words = line.split(" ");
			for (String word : words) {
				output.collect(new Text(word), ONE);
			}
		}
	}

}
