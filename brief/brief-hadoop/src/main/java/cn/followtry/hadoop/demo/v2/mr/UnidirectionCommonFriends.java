package cn.followtry.hadoop.demo.v2.mr;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;
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
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 获取任意两个人的共同朋友<br>
 * 
 * 单向好友，即B是A的好友也是C的好友，但A和C至少一个不是B的好友，那么AC的共同好友是B
 * @author jingzz
 * @since
 * 	0.0.3
 * @version
 * 	0.0.3
 */
public class UnidirectionCommonFriends {
	
	
	private static Logger LOGGER = LoggerFactory.getLogger(UnidirectionCommonFriends.class);
	
	static class UCFMapper extends Mapper<LongWritable, Text, Text, Text> {

        @Override
		protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, Text>.Context context)
				throws IOException, InterruptedException {

			StringTokenizer valueLine = new StringTokenizer(value.toString());

			// 获取每一行的自己
			String owner = null;
			if (valueLine.hasMoreTokens()) {
				owner = valueLine.nextToken();
			}

			// 获取每一行人员的朋友 k-v:B-A,即A将B视为朋友，但B不一定这样认为
			while (valueLine.hasMoreTokens()) {
				context.write(new Text(valueLine.nextToken()), new Text(owner));
			}
		}
	}
	
	static class UCFComBine extends Reducer<Text, Text, Text, Text>{

        @Override
        protected void reduce(Text key,Iterable<Text> values,Reducer<Text, Text, Text, Text>.Context context)
				throws IOException, InterruptedException {
			//翻转，计算任意两个人共同认同的朋友
			Iterator<Text> ite = values.iterator();
			Set<Object> set = new TreeSet<Object>();
			while (ite.hasNext()) {
				//提取出所有需要计算好友的人员
				Text person = (Text) ite.next();
				set.add(person.toString());
			}
			
			/*
			 * 计算两个人的共同好友，key即为两个人员的共同好友
			 */
			Object[] owners = set.toArray();
			for (int i = 0; i < owners.length - 1; i++) {
				for (int j = i + 1; j < owners.length; j++) {
					String ownerKey = (String)owners[i]+(String)owners[j];
					context.write(new Text(ownerKey.trim()), key);
				}
			}
		}
	}
	
	static class UCFReducer extends Reducer<Text, Text, Text, Text>{

        @Override
		protected void reduce(Text key, Iterable<Text> values, Reducer<Text, Text, Text, Text>.Context context)
				throws IOException, InterruptedException {
			StringBuilder cf = new StringBuilder();
			for (Text friend : values) {
				cf.append(friend.toString());
			}
			context.write(key, new Text(cf.toString()));
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

		Job job = Job.getInstance(conf, "Unidirection Common Friend num " + RandomUtils.nextInt());
		job.setJarByClass(UnidirectionCommonFriends.class);
		
		job.setMapperClass(UCFMapper.class);
		job.setCombinerClass(UCFComBine.class);
		job.setReducerClass(UCFReducer.class);
		
		
		job.setOutputValueClass(Text.class);
		job.setOutputKeyClass(Text.class);
		
		FileInputFormat.setInputPaths(job, inputPaths.toString());
		FileOutputFormat.setOutputPath(job, new Path(outputPath));
		
		System.exit(job.waitForCompletion(true) ? 0 : 1);
		
	}

}
