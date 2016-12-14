/**
 * 
 */
package cn.followtry.hadoop.demo.v2.mr;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

import cn.followtry.hadoop.demo.hdfs.HDFSOper;

/**
 * 
 *  brief-hadoop-demo/cn.followtry.hadoop.demo.v2.mr.WordCountV2
 * @author 
 *		jingzz 
 * @since 
 *		2016年12月14日 上午10:03:48
 */
public class WordCountTest {
	
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		String outpathDir = "/user/root/input";
		String inputPaths = "/user/root/output";
		//检查输出目录是否存在，存在则直接删除目录
//		HDFSOper.rmExistsOutputDir(outpathDir);
		Configuration conf = new Configuration();
		conf.set("mapreduce.framework.name", "yarn");  
//		conf.set("yarn.app.mapreduce.am.staging-dir", "c:\\users\\Administrator\\tmp\\hadoop-yarn\\staging");  
		conf.set("yarn.resourcemanager.address", "192.168.2.201:8032");
		Job job = Job.getInstance(conf, "wordCount v2 demo");
		
		job.setJarByClass(WordCountTest.class);
		
		job.setMapperClass(WordCountMapV2.class);
		job.setCombinerClass(WordCountReduceV2.class);
		job.setReducerClass(WordCountReduceV2.class);

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		FileInputFormat.setInputPaths(job, inputPaths );
		FileOutputFormat.setOutputPath(job, new Path(outpathDir));
		
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}
