package cn.followtry.hadoop.demo.v2.mr;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
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

import cn.followtry.hadoop.demo.hdfs.HDFSOper;
import cn.followtry.hadoop.demo.util.DebugConfUtil;
import cn.followtry.hadoop.demo.v2.mr.STjoin.STJoinMapper;
import cn.followtry.hadoop.demo.v2.mr.STjoin.STjoinReducer;

/**
 * 多表连表查询
 * 
 * @author jingzz
 * @since
 * 
 * @version
 * 
 */
public class MTjoin {

	private static final Logger LOGGER = LoggerFactory.getLogger(MTjoin.class);

	private static final String FACTORY_TABLE = "1";

	private static final String ADDRESS_TABLE = "2";

	private static final String SPLIT_CHAR = "+";

	static class MTjoinMapper extends Mapper<LongWritable, Text, Text, Text> {
		@Override
		protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, Text>.Context context)
				throws IOException, InterruptedException {
			String line = value.toString().trim();
			if (line.startsWith("factoryname") || line.startsWith("addressID")) {
				// 两个表的首行不处理
				return;
			}
			char firstChar = line.charAt(0);
			if (firstChar >= '0' && firstChar <= '9') {// 认为是地名表
				int splitLine = line.indexOf(" ");
				String addrID = line.substring(0, splitLine).trim();
				String addrName = line.substring(splitLine).trim();
				LOGGER.info("addrId={},addrName={}", addrID, addrName);
				context.write(new Text(addrID), new Text(ADDRESS_TABLE + SPLIT_CHAR + addrName));
			} else {// 认为是工厂表
				int splitLine = line.lastIndexOf(" ");
				String factoryName = line.substring(0, splitLine).trim();
				String addrID = line.substring(splitLine).trim();
				LOGGER.info("addrId={},factoryName={}", addrID, factoryName);
				context.write(new Text(addrID), new Text(FACTORY_TABLE + SPLIT_CHAR + factoryName));
			}
		}
	}

	static class MTjoinReducer extends Reducer<Text, Text, Text, Text> {
		@Override
		protected void reduce(Text key, Iterable<Text> values, Reducer<Text, Text, Text, Text>.Context context)
				throws IOException, InterruptedException {
			Iterator<Text> ite = values.iterator();
			List<String> addrNameList = new ArrayList<String>();
			List<String> factoryNameList = new ArrayList<String>();
			while (ite.hasNext()) {
				String nameLine = ite.next().toString().trim();
				if (nameLine.startsWith(ADDRESS_TABLE)) {
					addrNameList.add(nameLine.substring(2));
				} else if (nameLine.startsWith(FACTORY_TABLE)) {
					factoryNameList.add(nameLine.substring(2));
				} else {
					//不处理
				}

			}
			if (CollectionUtils.isNotEmpty(factoryNameList) && CollectionUtils.isNotEmpty(addrNameList)) {
				for (String factoryName : factoryNameList) {
					for (String addrName : addrNameList) {
						context.write(new Text(factoryName), new Text(addrName));
					}
				}
			}
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

		Job job = Job.getInstance(conf, "Multi table join " + RandomUtils.nextInt());

		job.setJarByClass(MTjoin.class);

		job.setMapperClass(MTjoinMapper.class);
		job.setReducerClass(MTjoinReducer.class);

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);

		FileInputFormat.setInputPaths(job, inputPaths.toString());
		FileOutputFormat.setOutputPath(job, new Path(outputPath));

		System.exit(job.waitForCompletion(true) ? 0 : 1);

	}
}
