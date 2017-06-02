
package cn.followtry.hadoop.demo.util;
/**
 * 远程调试时需要设置的MapReduce属性名称
 * @author 
 *		jingzz 
 * @since 
 *		
 * @version
 *    0.0.3
 */
public interface DebugConfig {
	
	/** mapreduce.framework.name **/
	String MAPREDUCE_FRAMEWORK_NAME = "mapreduce.framework.name";
	
	String MAPREDUCE_FRAMEWORK_NAME_DEFAULT_VALUE = "yarn";
	
	/** fs.defaultFS **/
	String FS_DEFAULTFS = "fs.defaultFS";
	
	String FS_DEFAULTFS_DEFAULT_VALUE_1 = "hdfs://"+HdfsHaElection.getActiveHaNode()+":8020";
	String FS_DEFAULTFS_DEFAULT_VALUE = "hdfs://h2m1:8020";

	/**mapred.remote.os */
	String MAPRED_REMOTE_OS = "mapred.remote.os";
	
	String MAPRED_REMOTE_OS_DEFAULT_VALUE = "Linux";

	/** mapreduce.app-submission.cross-platform */
	String MAPREDUCE_APP_SUBMISSION_CROSS_PALTFORM = "mapreduce.app-submission.cross-platform";
	
	String MAPREDUCE_APP_SUBMISSION_CROSS_PALTFORM_DEFAULT_VALUE = "true";

	/** yarn.resourcemanager.scheduler.address */
	String YARN_RESOURCEMANAGER_SCHEDULER_ADDRESS = "yarn.resourcemanager.scheduler.address";
	
	String YARN_RESOURCEMANAGER_SCHEDULER_ADDRESS_DEFAULT_VALUE = "h2m1:8030";

	/** yarn.resourcemanager.address */
	String YARN_RESOURCEMANAGER_ADDRESS = "yarn.resourcemanager.address";
	
	String YARN_RESOURCEMANAGER_ADDRESS_DEFAULT_VALUE = "192.168.2.201:8032";

	/** mapreduce.job.jar */
	String MAPREDUCE_JOB_JAR = "mapreduce.job.jar";
	
	String MAPREDUCE_JOB_JAR_DEFAULT_VALUE = "d:\\mapreduce.jar";
}
