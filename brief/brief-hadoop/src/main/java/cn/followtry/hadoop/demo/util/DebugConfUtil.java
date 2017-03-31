package cn.followtry.hadoop.demo.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * brief-hadoop-demo/cn.followtry.hadoop.demo.util.DebugConfUtil
 * 
 * @author jingzz
 * @since 2016年12月15日 下午12:32:02
 */
public class DebugConfUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(DebugConfUtil.class);

	private static Map<String, String> properties;
	
	private static final String OS_NAME = "os.name";

	private DebugConfUtil() {
	}

	static void initProp(Map<String, String> prop) {
		properties = prop;
	}

	/**
	 * 设置win本地调试提交MR程序到远程YARN集群上的配置信息
	 * 
	 * @author jingzz
	 * @param conf
	 *            传入Configuration类
	 * @param prop
	 *            自定义设置的配置
	 */
	public static void setLocalDebugConfiguration(Configuration conf, Map<String, String> prop) {

		initProp(prop);

		if (MapUtils.isNotEmpty(properties)) {

			setConfProp(conf, DebugConfig.MAPREDUCE_FRAMEWORK_NAME,
					DebugConfig.MAPREDUCE_FRAMEWORK_NAME_DEFAULT_VALUE);
			/*
			 * 避免/tmp/hadoop-yarn/staging/JING/.staging/job_1481726235168_0009/
			 * job.splitmetainfo找不到的错误
			 * value为hdfs-site.xml的dfs.namenode.rpc-address.ns1属性指定的值。
			 */
			setConfProp(conf, DebugConfig.FS_DEFAULTFS, DebugConfig.FS_DEFAULTFS_DEFAULT_VALUE);

			// 避免ExitCodeException exitCode=1: /bin/bash: line 0: fg: no job
			// control
			setConfProp(conf, DebugConfig.MAPRED_REMOTE_OS, DebugConfig.MAPRED_REMOTE_OS_DEFAULT_VALUE);
			setConfProp(conf, DebugConfig.MAPREDUCE_APP_SUBMISSION_CROSS_PALTFORM,
					DebugConfig.MAPREDUCE_APP_SUBMISSION_CROSS_PALTFORM_DEFAULT_VALUE);

			/*
			 * 避免Retrying connect to server: 0.0.0.0/0.0.0.0:8030
			 * 一般为默认设置，value为主yarn的resourcemanager节点的host:port
			 */
			setConfProp(conf, DebugConfig.YARN_RESOURCEMANAGER_SCHEDULER_ADDRESS,
					DebugConfig.YARN_RESOURCEMANAGER_SCHEDULER_ADDRESS_DEFAULT_VALUE);
			setConfProp(conf, DebugConfig.YARN_RESOURCEMANAGER_ADDRESS,
					DebugConfig.YARN_RESOURCEMANAGER_ADDRESS_DEFAULT_VALUE);

			/*
			 * 避免java.lang.ClassNotFoundException: Class
			 * cn.followtry.hadoop.demo.v2.mr.WordCountMapV2 not found问题 日志提示：No
			 * job jar file set. User classes may not be found. See
			 * JobConf(Class) or JobConf#setJar(String)
			 * 
			 * 所以不管是在Eclipse中直接调试运行还是上传到Yarn集群，都需要先打成Jar包。
			 * "mapred.jar"不需要动，只要将"d:\\mapreduce.jar"处替换为打好包的jar的位置。
			 * "mapred.jar" 已经过期，请替换为"mapreduce.job.jar"
			 * 
			 * 注意：在开发调试时，只要保证该jar包中含有指定的Type即可，在开发环境做的修改，可以在执行时直接反映在程序中，
			 * 而不需要每次运行都打成新的jar包。
			 */
			// setConfProp(conf, "mapred.jar", "d:\\mapreduce.jar");
			setConfProp(conf, DebugConfig.MAPREDUCE_JOB_JAR, null);
		}
	}
	
	/**
	 * 根据系统类型做配置，如果是非windows系统，直接返回。windows需要做部分配置后<br>
	 * 使用默认配置
	 * @author jingzz
	 * @param conf
	 */
	public static void confByOS(Configuration conf){
		confByOS(conf, null);
	}

	/**
	 * 根据系统类型做配置，如果是非windows系统，直接返回。windows需要做部分配置后
	 * 
	 * @author jingzz
	 * @param conf
	 * @param prop 远程调试MR程序是设置的MR配置属性。从{@link DebugConfig}接口中获取属性名及默认值
	 */
	public static void confByOS(Configuration conf,Map<String, String> prop) {
		// 只有在当前系统为windows是设置该debug配置
		String osName = System.getProperties().getProperty(OS_NAME).toLowerCase();
		if (osName.contains("windows")) {
			LOGGER.info("运行在windows平台，需要设置配置");
			if (prop == null) {
				prop = new HashMap<String,String>();
				prop.put(DebugConfig.MAPREDUCE_JOB_JAR, DebugConfig.MAPREDUCE_JOB_JAR_DEFAULT_VALUE);
			}
			setLocalDebugConfiguration(conf, prop);
		} else {
			LOGGER.info("运行在{}平台", osName);
		}
	}

	private static String getProperty(String key) {
		String value = properties.get(key);
		return StringUtils.isEmpty(value) ? null : value;
	}

	/**
	 * 设置配置的属性，如果没有设置则配置默认值，默认值也可能为null
	 */
	private static void setConfProp(Configuration conf, String key, String defaultValue) {
		String propertyValue = getProperty(key);
		if (propertyValue == null) {
			conf.set(key, defaultValue);
		} else {
			conf.set(key, propertyValue);
		}
	}

}
