package cn.followtry.hadoop.demo.v2.mr;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * 
 *  brief-hadoop-demo/cn.followtry.hadoop.demo.v2.mr.WordCountMapV2
 * @author 
 *		jingzz 
 * @since 
 *		2016年12月14日 上午10:25:07
 */
public class WordCountMapV2 extends Mapper<LongWritable, Text, Text, IntWritable>{

	private static final int ONE = 1;
	
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		String line = value.toString();
		if (StringUtils.isNotEmpty(line)) {
			String[] words = line.split(" ");
			for (String word : words) {
				context.write(new Text(word), new IntWritable(ONE));
			}
		}
	}
}
