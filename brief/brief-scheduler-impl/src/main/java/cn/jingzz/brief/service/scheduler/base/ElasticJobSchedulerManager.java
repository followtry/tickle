/**
 * 
 */
package cn.jingzz.brief.service.scheduler.base;

import com.dangdang.ddframe.job.api.ElasticJob;
import com.dangdang.ddframe.job.api.JobConfiguration;
import com.dangdang.ddframe.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.reg.zookeeper.ZookeeperRegistryCenter;

/**
 * 封装Elastic-Job框架的基本配置操作
 * @author jingzz
 * @time 2016年5月4日 上午9:31:49
 * @name service-scheduler-impl/com.yonyou.worktime.service.scheduler.base.ElasticJobSchedulerManager
 * @since 2016年5月4日 上午9:31:49
 */
public class ElasticJobSchedulerManager {
	
	/**
	 * 获取ZooKeeper注册中心
	 * @author jingzz
	 * @return
	 */
	public static ZookeeperRegistryCenter getZkRegCenter(){
		String serverLists = "localhost:2181";
		String namespace = "worktime_scheduler_center";
		int baseSleepTimeMilliseconds = 1000;
		int maxSleepTimeMilliseconds = 3000;
		int maxRetries = 3;
		ZookeeperConfiguration zkConfig = new ZookeeperConfiguration(serverLists, namespace, baseSleepTimeMilliseconds, maxSleepTimeMilliseconds, maxRetries);
		
		ZookeeperRegistryCenter zkRegCenter = new ZookeeperRegistryCenter(zkConfig);
		return zkRegCenter;
	}
	
	/**
	 * 获取JobConfiguration的配置信息
	 * @author jingzz
	 * @param jobName 作业名称
	 * @param jobClass 作业实现类名称
	 * @param shardingTotalCount 作业分片总数
	 * @param cron 作业启动时间的cron表达式
	 * @param jobParameter 作业自定义参数
	 * @return
	 */
	public static JobConfiguration getJobConfig(String jobName, Class<? extends ElasticJob> jobClass, int shardingTotalCount, String cron,String jobParameter ) {
		JobConfiguration jobConfig = new JobConfiguration(jobName, jobClass, shardingTotalCount, cron);
		jobConfig.setJobParameter(jobParameter);
		return jobConfig;
	}
}
