package cn.followtry.hadoop.demo.v2.mr;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import cn.followtry.hadoop.demo.hdfs.HDFSOper;
import cn.followtry.hadoop.demo.util.DebugConfUtil;
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

/**
 * @author jingzz
 * @since
 * 
 * @version
 * 
 */
public class STjoin {

	private static final String LEFT_TABLE_TYPE = "1";

	private static final String RIGHT_TABLE_TYPE = "2";

	private static int time = 0;
	
	private static final String SPLIT_CHAR = "+";

	private static final Logger LOGGER = LoggerFactory.getLogger(STjoin.class);

	static class STJoinMapper extends Mapper<LongWritable, Text, Text, Text> {

		String childName;// 子女名称

		String parentName;// 父母名称

		String relationType;// 关联类型


        @Override
		protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, Text>.Context context)
				throws IOException, InterruptedException {
			if (key.get() > 0) {
				StringTokenizer st = new StringTokenizer(value.toString().trim(), " ");
				while (st.hasMoreElements()) {
					childName = st.nextToken();
					parentName = st.nextToken();

					// 左表
					context.write(new Text(parentName), new Text(LEFT_TABLE_TYPE + SPLIT_CHAR + childName));

					// 右表
					context.write(new Text(childName),
							new Text(RIGHT_TABLE_TYPE + SPLIT_CHAR + parentName));
				}
			}
		}
	}

	static class STjoinReducer extends Reducer<Text, Text, Text, Text> {

        @Override
		protected void reduce(Text key, Iterable<Text> value, Reducer<Text, Text, Text, Text>.Context context)
				throws IOException, InterruptedException {
			if (time == 0) {
				context.write(new Text("grandchild"), new Text("grandparent"));
				time++;
			}
			
			List<String> grandchildNameList = new ArrayList<String>();
			List<String> grandparentNameList = new ArrayList<String>();
			
			Iterator<Text> ite = value.iterator();
			while (ite.hasNext()) {
				//一条记录
				String record = ite.next().toString().trim();
				if (record.length() == 0) {
					continue;
				} else {
					if (record.startsWith(LEFT_TABLE_TYPE)) {
						String childName = record.substring(record.lastIndexOf(SPLIT_CHAR)+1);
						grandchildNameList.add(childName);
					}else if (record.startsWith(RIGHT_TABLE_TYPE)) {
						String parentName = record.substring(record.lastIndexOf(SPLIT_CHAR) + 1);
						grandparentNameList.add(parentName);
					}
				}
			}
			writeReducerResult(context, grandchildNameList, grandparentNameList);
		}

		/**
		 * @author jingzz
		 * @param context
		 * @param grandchildNameList
		 * @param grandparentNameList
		 * @throws IOException
		 * @throws InterruptedException
		 */
		private void writeReducerResult(Reducer<Text, Text, Text, Text>.Context context,
				List<String> grandchildNameList, List<String> grandparentNameList)
						throws IOException, InterruptedException {
			if (CollectionUtils.isNotEmpty(grandchildNameList) && CollectionUtils.isNotEmpty(grandparentNameList)) {
				for (String grandchildName : grandchildNameList) {
					for (String grandparentName : grandparentNameList) {
						context.write(new Text(grandchildName), new Text(grandparentName));
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
			if (i < inputNum -1) {
				inputPaths.append(",");
			}
		}
		
		String outputPath = otherArgs[inputNum];
		
		HDFSOper.rmExistsOutputDir(outputPath);
		
		DebugConfUtil.confByOS(conf);
		
		Job job = Job.getInstance(conf, "single table join"+RandomUtils.nextInt());
		
		job.setJarByClass(STjoin.class);
		
		job.setMapperClass(STJoinMapper.class);
		job.setReducerClass(STjoinReducer.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		
		FileInputFormat.setInputPaths(job, inputPaths.toString());
		FileOutputFormat.setOutputPath(job, new Path(outputPath));
		
		System.exit(job.waitForCompletion(true) ? 0 : 1);
		
		
	}

}
