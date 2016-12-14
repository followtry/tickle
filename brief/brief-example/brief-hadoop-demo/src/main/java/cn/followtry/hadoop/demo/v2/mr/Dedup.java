package cn.followtry.hadoop.demo.v2.mr;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.TextOutputFormat;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 *  brief-hadoop-demo/cn.followtry.hadoop.demo.v2.mr.Dedup
 * @author 
 *		jingzz 
 * @since 
 *		2016年12月14日 下午2:35:49
 */
public class Dedup {
	
	private static final IntWritable ONE = new IntWritable(1);
	
	static class DedupMapper extends Mapper<LongWritable, Text, Text, IntWritable>{
		@Override
		protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context)
				throws IOException, InterruptedException {
			context.write(value, ONE);
		}
	}
	
	static class DedupReducer extends Reducer<Text, IntWritable, Text, IntWritable>{
		@Override
		protected void reduce(Text key, Iterable<IntWritable> values,
				Reducer<Text, IntWritable, Text, IntWritable>.Context context) throws IOException, InterruptedException {
			context.write(key, ONE);
		}
	}
	
	public static void main(String[] args) throws IOException {
		Configuration conf = new Configuration();
		
		String jobName ="数据去重";
		Job job = new Job(conf, jobName);
		
		job.setJarByClass(Dedup.class);
		job.setMapOutputKeyClass(DedupMapper.class);
		job.setCombinerClass(DedupReducer.class);
		job.setReducerClass(DedupReducer.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
	}
}
