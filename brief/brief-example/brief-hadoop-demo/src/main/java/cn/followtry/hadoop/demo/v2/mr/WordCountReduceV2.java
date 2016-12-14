package cn.followtry.hadoop.demo.v2.mr;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 *  brief-hadoop-demo/cn.followtry.hadoop.demo.v2.mr.WordCountReduceV2
 * @author 
 *		jingzz 
 * @since 
 *		2016年12月14日 上午10:25:28
 */
public class WordCountReduceV2 extends Reducer<Text, IntWritable, Text, IntWritable> {

	private static final Logger LOGGER = LoggerFactory.getLogger(WordCountReduceV2.class);
	
	@Override
	protected void reduce(Text key, Iterable<IntWritable> values,
			Reducer<Text, IntWritable, Text, IntWritable>.Context context) throws IOException, InterruptedException {
		int count = 0;
		for (IntWritable intValue : values) {
			count += intValue.get();
		}
		LOGGER.info("统计{}的次数为{}", key, count);
		context.write(key, new IntWritable(count));
	}

}
