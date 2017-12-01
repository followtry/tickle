package cn.followtry.hadoop.demo.v2.mr;

import java.io.IOException;
import cn.followtry.hadoop.demo.hdfs.HDFSOper;
import cn.followtry.hadoop.demo.util.DebugConfUtil;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 排序
 *  brief-hadoop-demo/cn.followtry.hadoop.demo.v2.mr.Sort
 * @author 
 *		jingzz 
 * @since 
 *		2016年12月15日 下午3:31:57
 */
public class Sort {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Sort.class);
	
	private static final IntWritable ONE = new IntWritable(1);
	
	static class SortMapper extends Mapper<LongWritable, Text, IntWritable, IntWritable>{

        @Override
		protected void map(LongWritable key, Text value,
				Mapper<LongWritable, Text, IntWritable, IntWritable>.Context context)
						throws IOException, InterruptedException {
			String num = value.toString();
			Integer integer;
			try {
				integer = Integer.valueOf(num);
			} catch (NumberFormatException e) {
				integer = null;
				return ;
			}
			IntWritable numValue = new IntWritable(integer);
			context.write(numValue, ONE);
		}
	}
	
	static class SortReducer extends Reducer<IntWritable, IntWritable, IntWritable, IntWritable>{
		
		private static IntWritable lineNum  = new IntWritable(1); 

        @Override
		protected void reduce(IntWritable key, Iterable<IntWritable> values,
				Reducer<IntWritable, IntWritable, IntWritable, IntWritable>.Context context)
						throws IOException, InterruptedException {
			for (IntWritable value : values) {
				value.get();
				//k-v 排名(行号，数值)
				context.write(lineNum, key);
				lineNum.set(lineNum.get() + 1);
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
		String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
		if (otherArgs == null || otherArgs.length < 2) {
			LOGGER.error("用法：参数最少为两个，最后一个为输出目录，其他为输入路径");
			System.exit(-1);
		}
		
		int inputNum = otherArgs.length - 1;
		StringBuilder inputPaths = new StringBuilder();
		for (int i = 0; i < inputNum; i++) {
			inputPaths.append(otherArgs[i]);
			if (i < inputNum -1) {
				inputPaths.append(",");
			}
		}
		
		String outputPath = otherArgs[inputNum];
		
		HDFSOper.rmExistsOutputDir(outputPath);
		
		DebugConfUtil.confByOS(conf);
		
		Job job = Job.getInstance(conf, "MR Sort; Num=" + RandomUtils.nextInt());
		
		job.setJarByClass(Sort.class);
		
		job.setMapperClass(SortMapper.class);
		job.setReducerClass(SortReducer.class);
		
		job.setOutputKeyClass(IntWritable.class);
		job.setOutputValueClass(IntWritable.class);
		
		FileInputFormat.setInputPaths(job, inputPaths.toString());
		FileOutputFormat.setOutputPath(job, new Path(outputPath));
		
		System.exit(job.waitForCompletion(true) ? 0 : 1);
		
	}
}
