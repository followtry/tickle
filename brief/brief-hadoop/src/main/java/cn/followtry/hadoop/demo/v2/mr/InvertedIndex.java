package cn.followtry.hadoop.demo.v2.mr;

import java.io.IOException;
import java.util.StringTokenizer;
import cn.followtry.hadoop.demo.hdfs.HDFSOper;
import cn.followtry.hadoop.demo.util.DebugConfUtil;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 计算倒排索引
 * 
 * @author jingzz
 * @since
 * 
 * @version
 * 
 */
public class InvertedIndex {

	private static final Logger LOGGER = LoggerFactory.getLogger(InvertedIndex.class);

	private static final String SPLIT_CHAR = ":";

	static class InvertedIndexMapper extends Mapper<LongWritable, Text, Text, Text> {

		private Text keyInfo = new Text();

		private Text valueInfo = new Text();

		private FileSplit split;


        @Override
		protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, Text>.Context context)
				throws IOException, InterruptedException {
			split = (FileSplit) context.getInputSplit();
			String fileName = split.getPath().getName();
			StringTokenizer st = new StringTokenizer(value.toString());
			while (st.hasMoreElements()) {
				keyInfo.set(st.nextElement() + SPLIT_CHAR + fileName);
				valueInfo.set("1");
				context.write(keyInfo, valueInfo);
			}
		}
	}

	static class Combine extends Reducer<Text, Text, Text, Text> {

        @Override
		protected void reduce(Text key, Iterable<Text> values, Reducer<Text, Text, Text, Text>.Context context)
				throws IOException, InterruptedException {
			String k = key.toString();
			int i = 0;
			for (@SuppressWarnings("unused") Text value : values) {
				i++;
			}
			String[] keys = k.split(SPLIT_CHAR);
			context.write(new Text(keys[0]), new Text(keys[1] + SPLIT_CHAR + i));
		}
	}
	
	static class InvertedIndexReducer extends Reducer<Text, Text, Text, Text>{

        @Override
		protected void reduce(Text key, Iterable<Text> values, Reducer<Text, Text, Text, Text>.Context context)
				throws IOException, InterruptedException {
			StringBuilder sb = new StringBuilder();
			for (Text value : values) {
				sb.append(value).append(";");
			}
			context.write(new Text(key), new Text(sb.toString()));
		}
	}

	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
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

		Job job = Job.getInstance(conf, "Inverted index num " + RandomUtils.nextInt());

		job.setJarByClass(InvertedIndex.class);

		job.setMapperClass(InvertedIndexMapper.class);
		job.setCombinerClass(Combine.class);
		job.setReducerClass(InvertedIndexReducer.class);

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);

		FileInputFormat.setInputPaths(job, inputPaths.toString());
		FileOutputFormat.setOutputPath(job, new Path(outputPath));

		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}
