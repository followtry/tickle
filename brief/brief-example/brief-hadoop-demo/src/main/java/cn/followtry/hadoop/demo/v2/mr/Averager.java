package cn.followtry.hadoop.demo.v2.mr;

import java.io.IOException;

import org.apache.commons.lang.math.RandomUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
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

import cn.followtry.hadoop.demo.hdfs.HDFSOper;
import cn.followtry.hadoop.demo.util.DebugConfUtil;

/**
 * brief-hadoop-demo/cn.followtry.hadoop.demo.v2.mr.Averager
 * 
 * @author jingzz
 * @since 2016年12月15日 下午4:32:16
 */
public class Averager {

	private static final Logger LOGGER = LoggerFactory.getLogger(Averager.class);

	static class AveragerMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

		private Text name = new Text();

		@Override
		protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context)
				throws IOException, InterruptedException {
			String lineData = value.toString();
			LOGGER.info("mapper每一行数据:{}", lineData);
			String[] lineDatas = lineData.split(",");
			name.set(lineDatas[0]);
			context.write(name, new IntWritable(Integer.parseInt(lineDatas[1])));
		}
	}

	static class AverageReduce extends Reducer<Text, IntWritable, Text, DoubleWritable> {
		@Override
		protected void reduce(Text key, Iterable<IntWritable> values,
				Reducer<Text, IntWritable, Text, DoubleWritable>.Context context)
						throws IOException, InterruptedException {
			// 计算每个人的成绩
			int i = 0;
			int sum = 0;
			for (IntWritable score : values) {
				i++;
				sum += score.get();
			}
			double averageScore = (double) sum / i;
			LOGGER.info("reduce{} 的平均成绩为{}", key, averageScore);
			context.write(key, new DoubleWritable(averageScore));

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
			if (i < inputNum - 1) {
				inputPaths.append(",");
			}
		}

		String outputPath = otherArgs[inputNum];

		HDFSOper.rmExistsOutputDir(outputPath);

		DebugConfUtil.confByOS(conf);

		Job job = Job.getInstance(conf, "average scoe " + RandomUtils.nextInt());

		job.setJarByClass(Averager.class);

		job.setMapperClass(AveragerMapper.class);
		job.setReducerClass(AverageReduce.class);
		
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(DoubleWritable.class);

		FileInputFormat.setInputPaths(job, inputPaths.toString());
		FileOutputFormat.setOutputPath(job, new Path(outputPath));

		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}
