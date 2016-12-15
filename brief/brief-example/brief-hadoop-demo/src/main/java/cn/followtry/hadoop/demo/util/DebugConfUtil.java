package cn.followtry.hadoop.demo.util;

import org.apache.hadoop.conf.Configuration;

/**
 *  brief-hadoop-demo/cn.followtry.hadoop.demo.util.DebugConfUtil
 * @author 
 *		jingzz 
 * @since 
 *		2016年12月15日 下午12:32:02
 */
public class DebugConfUtil {
	
	private DebugConfUtil() {
	}
	
	/**
	 * 设置win本地调试提交MR程序到远程YARN集群上的配置信息
	 * @author jingzz
	 * @param jarPath 生成的jar的位置
	 * @param conf 传入Configuration类
	 */
	public static void setLocalDebugConfiguration(String jarPath, Configuration conf) {
		
		conf.set("mapreduce.framework.name", "yarn");  
		/*
		 * 避免/tmp/hadoop-yarn/staging/JING/.staging/job_1481726235168_0009/job.splitmetainfo找不到的错误
		 * value为hdfs-site.xml的dfs.namenode.rpc-address.ns1属性指定的值。
		 */
		conf.set("fs.defaultFS", "hdfs://h2m1:8220");  
		
		//避免ExitCodeException exitCode=1: /bin/bash: line 0: fg: no job control
		conf.set("mapred.remote.os", "Linux");  
		conf.set("mapreduce.app-submission.cross-platform", "true"); 
		
		/*
		 * 避免Retrying connect to server: 0.0.0.0/0.0.0.0:8030
		 * 一般为默认设置，value为主yarn的resourcemanager节点的host:port
		 */
		conf.set("yarn.resourcemanager.scheduler.address", "h2m1:8030");  
		conf.set("yarn.resourcemanager.address", "192.168.2.201:8032");
		
		/*
		 * 避免java.lang.ClassNotFoundException: Class cn.followtry.hadoop.demo.v2.mr.WordCountMapV2 not found问题
		 * 日志提示：No job jar file set.  User classes may not be found. See JobConf(Class) or JobConf#setJar(String)
		 * 所以不管是在Eclipse中直接调试运行还是上传到Yarn集群，都需要先打成Jar包。
		 * "mapred.jar"不需要动，只要将"d:\\mapreduce.jar"处替换为打好包的jar的位置。
		 * "mapred.jar" 已经过期，请替换为"mapreduce.job.jar"
		 */
		//conf.set("mapred.jar", "d:\\mapreduce.jar");
		conf.set("mapreduce.job.jar", jarPath);
	}
	
}
