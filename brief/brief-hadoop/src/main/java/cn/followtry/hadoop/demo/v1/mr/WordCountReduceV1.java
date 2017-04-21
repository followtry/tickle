/**
 * 
 */
package cn.followtry.hadoop.demo.v1.mr;

import java.io.IOException;
import java.util.Iterator;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jingzz
 * @time 2016年12月13日 下午6:52:38
 * @name brief-hadoop-demo/cn.followtry.hadoop.demo.mr.WordCountReduce
 * @since 2016年12月13日 下午6:52:38
 */
public class WordCountReduceV1 extends MapReduceBase implements Reducer<Text, IntWritable, Text, IntWritable> {

	private static final Logger LOGGER = LoggerFactory.getLogger(WordCountReduceV1.class);

	@Override
	public void reduce(Text key, Iterator<IntWritable> values, OutputCollector<Text, IntWritable> output,
			Reporter reporter) throws IOException {
		int count = 0;
		while (values.hasNext()) {
			values.next();
			count++;
		}
		LOGGER.info("统计{}的次数为{}", key, count);
		output.collect(key, new IntWritable(count));
	}

}
