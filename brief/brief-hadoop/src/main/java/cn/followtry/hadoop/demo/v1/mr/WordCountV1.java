/**
 * 
 */
package cn.followtry.hadoop.demo.v1.mr;

import java.io.IOException;
import cn.followtry.hadoop.demo.hdfs.HDFSOper;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.TextInputFormat;
import org.apache.hadoop.mapred.TextOutputFormat;

/**
 * @author jingzz
 * @time 2016年12月13日 下午6:44:15
 * @name brief-hadoop-demo/cn.followtry.hadoop.demo.mr.WordCount
 * @since 2016年12月13日 下午6:44:15
 */
public class WordCountV1 {
	
	public static void main(String[] args) throws IOException {
		if (args == null || args.length < 2) {
			System.out.println("用法：\n"
					+ "     至少需要两个参数,最后一个为输出目录，其他为输入文件路径");
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
		HDFSOper.rmExistsOutputDir(outpathDir);
		
		JobConf conf = new JobConf(WordCountV1.class);
		
		//设置job的name
		conf.setJobName("word count mapreduce demo");
		
		//设置map和reduce
		conf.setMapperClass(WordCountMapperV1.class);
        conf.setReducerClass(WordCountReduceV1.class);
        
        //设置输出的key和value类型
        conf.setOutputKeyClass(Text.class);
        conf.setOutputValueClass(IntWritable.class);
        
        //设置输入输出格式化类型
        conf.setInputFormat(TextInputFormat.class);
        conf.setOutputFormat(TextOutputFormat.class);
		//在命令行mainclass后的第一个参数作为输入参数
        FileInputFormat.setInputPaths(conf, inputPaths.toString());
		//在命令行mainclass后的第二个参数作为输出参数
        FileOutputFormat.setOutputPath(conf, new Path(outpathDir));
        
        //提交任务
        JobClient.runJob(conf);
	}
}
