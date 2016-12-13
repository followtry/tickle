package cn.followtry.hadoop.demo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Counter;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloHadoop {

	private static final Logger LOGGER = LoggerFactory.getLogger(HelloHadoop.class);

	public static class TokenizerMapper extends Mapper<Object, Text, Text, IntWritable> {

		static enum CountersEnum {
			INPUT_WORDS
		}

		private final static IntWritable one = new IntWritable(1);
		private Text word = new Text();

		private boolean caseSensitive;
		private Set<String> patternsToSkip = new HashSet<String>();

		private Configuration conf;
		private BufferedReader fis;

		@Override
		public void setup(Context context) throws IOException, InterruptedException {
			conf = context.getConfiguration();
			caseSensitive = conf.getBoolean("wordcount.case.sensitive", true);
			if (conf.getBoolean("wordcount.skip.patterns", true)) {
				URI[] patternsURIs = Job.getInstance(conf).getCacheFiles();
				for (URI patternsURI : patternsURIs) {
					Path patternsPath = new Path(patternsURI.getPath());
					String patternsFileName = patternsPath.getName().toString();
					parseSkipFile(patternsFileName);
				}
			}
		}

		private void parseSkipFile(String fileName) {
			try {
				fis = new BufferedReader(new FileReader(fileName));
				String pattern = null;
				while ((pattern = fis.readLine()) != null) {
					patternsToSkip.add(pattern);
				}
			} catch (IOException ioe) {
				System.err.println(
						"Caught exception while parsing the cached file '" + StringUtils.stringifyException(ioe));
			}
		}

		@Override
		public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
			String line = (caseSensitive) ? value.toString() : value.toString().toLowerCase();
			for (String pattern : patternsToSkip) {
				line = line.replaceAll(pattern, "");
			}
			StringTokenizer itr = new StringTokenizer(line);
			while (itr.hasMoreTokens()) {
				word.set(itr.nextToken());
				context.write(word, one);
				Counter counter = context.getCounter(CountersEnum.class.getName(), CountersEnum.INPUT_WORDS.toString());
				counter.increment(1);
			}
		}
	}

	public static class IntSumReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
		private IntWritable result = new IntWritable();

		public void reduce(Text key, Iterable<IntWritable> values, Context context)
				throws IOException, InterruptedException {
			int sum = 0;
			for (IntWritable val : values) {
				sum += val.get();
			}
			result.set(sum);
			context.write(key, result);
		}
	}

	private static final String preifx_path = "webhdfs://h2m1:50070/webhdfs/v1/user/root";
	private static final String output_path = preifx_path + "/input/";
	private static final String input_path = preifx_path + "/output";
	private static final String target_path = preifx_path + "/";
	private static String path = "/";

	public static void main(String[] args) throws Exception {

		rmExistsOutputDir("/user/root/output");
		// 文件操作
		// fsOperator();
		// commitJob();
	}

	private static void commitJob() throws IOException, InterruptedException, ClassNotFoundException {
		Configuration conf = new Configuration();

		Job job = Job.getInstance(conf, "word count");
		job.setJarByClass(HelloHadoop.class);
		job.setMapperClass(TokenizerMapper.class);
		job.setCombinerClass(IntSumReducer.class);
		job.setReducerClass(IntSumReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);

		FileInputFormat.addInputPath(job, new Path(input_path));
		FileOutputFormat.setOutputPath(job, new Path(output_path));

		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}

	private static void rmExistsOutputDir(String outpathDir) throws FileNotFoundException, IOException {
		// 将本地文件上传到hdfs。
		Configuration config = new Configuration();
		FileSystem fs = FileSystem.get(URI.create("webhdfs://h2m1:50070"), config);
		Path output = new Path(outpathDir);
		if (fs.exists(output)) {
			LOGGER.info("目录{}已经存在,正在删除...", outpathDir);
			System.out.println("目录" + outpathDir + "已经存在,正在删除...");
			if (fs.delete(output, true)) {
				System.out.println("目录" + outpathDir + "已经删除");
				LOGGER.info("目录{}已经删除", outpathDir);
			}else {
				System.out.println("目录" + outpathDir + "删除失败");
				LOGGER.info("目录{}删除失败", outpathDir);
				
			}
		} else {
			System.out.println("目录" + outpathDir + "不存在");
			LOGGER.info("目录{}不存在", outpathDir);
		}
	}

	private static void fsOperator() throws FileNotFoundException, IOException {
		// 将本地文件上传到hdfs。
		Configuration config = new Configuration();
		FileSystem fs = FileSystem.get(URI.create("webhdfs://h2m1:50070"), config);
		int fileNum = 0;
		int level = 0;
		fileNum += listFiles(fs, path, level);
		System.out.println("-----------------------------------------------------");
		System.out.println("总文件数为：" + fileNum + "个");
	}

	private static int listFiles(FileSystem fs, String path, int level) throws FileNotFoundException, IOException {
		int fileNum = 0;
		String tempPath = path;
		StringBuilder prefix = new StringBuilder();
		if (level > 0) {
			for (int i = 0; i < level; i++) {
				prefix.append("    ");
			}
		}
		FileStatus[] listStatus = fs.listStatus(new Path(tempPath));
		for (FileStatus f : listStatus) {
			String dir = f.isDirectory() ? "目录" : "文件";
			String name = f.getPath().getName();
			String realPath = f.getPath().toString();
			System.out.println((level + 1) + "层：" + dir + ":" + name);
			System.out.println(prefix + "路径:" + realPath);
			System.out.println(prefix + "访问时间：" + f.getAccessTime());
			System.out.println(prefix + "块大小:" + f.getBlockSize());
			System.out.println(prefix + "所属组:" + f.getGroup());
			System.out.println(prefix + "长度:" + f.getLen());
			System.out.println(prefix + "修改时间:" + f.getModificationTime());
			System.out.println(prefix + "所有者:" + f.getOwner());
			System.out.println(prefix + "权限:" + f.getPermission());
			System.out.println(prefix + "副本:" + f.getReplication());
			System.out.println(prefix + "============================================================");
			if (f.isDirectory()) {
				fileNum += listFiles(fs, realPath, level + 1);
			} else {
				fileNum++;
			}
		}
		return fileNum;
	}
}
