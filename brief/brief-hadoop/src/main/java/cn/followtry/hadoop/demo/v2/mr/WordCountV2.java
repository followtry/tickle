/**
 * 
 */
package cn.followtry.hadoop.demo.v2.mr;

import java.io.IOException;
import cn.followtry.hadoop.demo.hdfs.HDFSOper;
import cn.followtry.hadoop.demo.util.DebugConfUtil;
import org.apache.commons.lang.StringUtils;
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

/**
 * 
 * brief-hadoop-demo/cn.followtry.hadoop.demo.v2.mr.WordCountV2
 * 
 * @author jingzz
 * @since 2016年12月14日 上午10:03:48
 */
public class WordCountV2 {
	
	private static final IntWritable ONE = new IntWritable(1);
	
	static class WordCountMapV2 extends Mapper<LongWritable, Text, Text, IntWritable>{
		
		@Override
		protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context)
				throws IOException, InterruptedException {
			String line = value.toString();
			if (StringUtils.isNotEmpty(line)) {
				String[] words = line.split(" ");
				for (String word : words) {
					context.write(new Text(word), ONE);
				}
			}
		}
	}
	
	static class WordCountReduceV2 extends Reducer<Text, IntWritable, Text, IntWritable> {
		@Override
		protected void reduce(Text key, Iterable<IntWritable> values,
				Reducer<Text, IntWritable, Text, IntWritable>.Context context) throws IOException, InterruptedException {
			int count = 0;
			for (IntWritable intValue : values) {
				count += intValue.get();
			}
			context.write(key, new IntWritable(count));
		}

	}

	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		
		/**
		 * 步骤1.获取输入输出路径参数
		 * 步骤2.删除已经存在输出目录
		 * 步骤3.根据系统类型进行configuration的配置
		 * 步骤4.获取Job实例
		 * 步骤5.为Job配置Jar,MapperClass,CombinerClas,ReducerClass
		 * 步骤6.为Job配置输出的key和value类型
		 * 步骤7.设置文件格式化
		 * 步骤8.提交Job并等待完成
		 */
		
		Configuration conf = new Configuration();
		String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
		if (otherArgs == null || otherArgs.length < 2) {
			System.out.println("用法：\n" + "     至少需要两个参数,最后一个为输出目录，其他为输入文件路径");
			System.exit(-1);
		}
		StringBuilder inputPaths = new StringBuilder();
		String outpathDir;
		int len = otherArgs.length - 1;
		for (int i = 0; i < len; i++) {
			inputPaths.append(otherArgs[i]);
			if (i < len - 1) {
				inputPaths.append(",");
			}
		}
		outpathDir = otherArgs[len];
		// 检查输出目录是否存在，存在则直接删除目录
		HDFSOper.rmExistsOutputDir(outpathDir);

		DebugConfUtil.confByOS(conf);
		
		Job job = Job.getInstance(conf, "wordCount v2 demo 2");

		job.setJarByClass(WordCountV2.class);

		job.setMapperClass(WordCountMapV2.class);
		job.setCombinerClass(WordCountReduceV2.class);
		job.setReducerClass(WordCountReduceV2.class);

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);

		FileInputFormat.setInputPaths(job, inputPaths.toString());
		FileOutputFormat.setOutputPath(job, new Path(outpathDir));

		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}
