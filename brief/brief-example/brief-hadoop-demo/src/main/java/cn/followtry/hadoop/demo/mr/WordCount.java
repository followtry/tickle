/**
 * 
 */
package cn.followtry.hadoop.demo.mr;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.followtry.hadoop.demo.HelloHadoop;

/**
 * @author jingzz
 * @time 2016年12月13日 下午6:44:15
 * @name brief-hadoop-demo/cn.followtry.hadoop.demo.mr.WordCount
 * @since 2016年12月13日 下午6:44:15
 */
public class WordCount {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(HelloHadoop.class);
	
	public static void main(String[] args) throws IOException {
		if (args == null || args.length < 2) {
			System.out.println("用法：至少需要两个参数,最后一个为输出目录，其他为输入文件路径");
			System.exit(-1);
		}
		StringBuilder inputPaths = new StringBuilder();
		String outpathDir;
		int len = args.length - 1;
		for (int i = 0; i < len; i++) {
			inputPaths.append(args[i]);
			if (i < len - 1) {
				inputPaths.append(",");
			}
		}
		outpathDir = args[len];
		//检查输出目录是否存在，存在则直接删除目录
		rmExistsOutputDir(outpathDir);
		JobConf conf = new JobConf(WordCount.class);
		conf.setJobName("word count mapreduce demo");
		conf.setMapperClass(WordCountMapper.class);
        conf.setReducerClass(WordCountReduce.class);
        conf.setOutputKeyClass(Text.class);
        conf.setOutputValueClass(IntWritable.class);
        
		//在命令行mainclass后的第一个参数作为输入参数
        FileInputFormat.setInputPaths(conf, inputPaths.toString());
		//在命令行mainclass后的第二个参数作为输出参数
        FileOutputFormat.setOutputPath(conf, new Path(outpathDir));
        
        JobClient.runJob(conf);
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
}
