package cn.followtry.hadoop.demo.v2.mr;

import java.io.IOException;
import cn.followtry.hadoop.demo.hdfs.HDFSOper;
import cn.followtry.hadoop.demo.util.DebugConfUtil;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
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
 * 数据去重
 *  brief-hadoop-demo/cn.followtry.hadoop.demo.v2.mr.Dedup
 * @author 
 *		jingzz 
 * @since 
 *		2016年12月14日 下午2:35:49
 */
public class Dedup {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Dedup.class);
	
	static class DedupMapper extends Mapper<LongWritable, Text, Text, NullWritable>{
		@Override
		protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, NullWritable>.Context context)
				throws IOException, InterruptedException {
			context.write(value, NullWritable.get());
		}
	}
	
	static class DedupReducer extends Reducer<Text, NullWritable, Text, NullWritable>{
		@Override
		protected void reduce(Text key, Iterable<NullWritable> values,Reducer<Text, NullWritable, Text, NullWritable>.Context context) throws IOException, InterruptedException {
			context.write(key, NullWritable.get());
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		//1.获取参数
		Configuration conf = new Configuration();
		String[] otherArgs = new GenericOptionsParser(conf,args).getRemainingArgs();
		//1.0 对于不符合参数数量的给出提示
		if (otherArgs == null || otherArgs.length < 2) {
			LOGGER.error("用法：\n"
						+ "   至少有两个参数，最后一个为输出目录，其他为输入源！");
			System.exit(-1);
		}

		//1.1 分别获取输入和输出路径
		//1.1.1 获取输入路径
		int inputNum = otherArgs.length - 1;
		StringBuilder inputPaths = new StringBuilder();
		for (int i = 0; i < inputNum; i++) {
			inputPaths.append(args[i]);
			if (i < inputNum - 1) {//最后一个输入参数后不添加逗号分隔符
				inputPaths.append(",");
			}
		}
		//1.1.2 获取输出路径
		String outputPath = otherArgs[inputNum];
		
		//2.删除已经存在的输出路径
		HDFSOper.rmExistsOutputDir(outputPath);
		
		//3.根据系统类型进行配置
		DebugConfUtil.confByOS(conf);
		
		String jobName = "my dedup mapreduce" + RandomUtils.nextInt();
		//4.获取Job实例
		Job job = Job.getInstance(conf, jobName );
		
		//5.为Job配置Jar,MapperClass,CombinerClas,ReducerClass
		job.setJarByClass(Dedup.class);
		job.setMapperClass(DedupMapper.class);
		job.setCombinerClass(DedupReducer.class);
		job.setReducerClass(DedupReducer.class);
		
		//6.为Job配置输出的key和value类型
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(NullWritable.class);
		
		//7.设置job任务的文件输入和输出目录
		FileInputFormat.setInputPaths(job, inputPaths.toString());
		FileOutputFormat.setOutputPath(job, new Path(outputPath));
		
		//8.提交任务并等待
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}
