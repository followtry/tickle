package cn.followtry.service.scheduler.base;

import com.dangdang.ddframe.job.api.JobConfiguration;
import com.dangdang.ddframe.job.internal.schedule.JobRegistry;
import com.dangdang.ddframe.job.internal.schedule.JobScheduleController;
import com.dangdang.ddframe.reg.zookeeper.ZookeeperConfiguration;

/**
 * . 封装Elastic-Job框架的基本配置操作。
 *
 * @author jingzz
 * @since 2016年5月4日 上午9:31:49
 */
public class ElasticJobSchedulerManager {

  /**
   * . 获取ZooKeeper注册中心
   */
  public static com.dangdang.ddframe.reg.zookeeper.ZookeeperRegistryCenter getZkRegCenter() {
    String serverLists = "192.168.100.1:2181";
    String namespace = "worktime_scheduler_center";
    int baseSleepTimeMilliseconds = 1000;
    int maxSleepTimeMilliseconds = 3000;
    int maxRetries = 3;
    ZookeeperConfiguration zkConfig = new ZookeeperConfiguration(serverLists, namespace,
            baseSleepTimeMilliseconds, maxSleepTimeMilliseconds, maxRetries);

    com.dangdang.ddframe.reg.zookeeper.ZookeeperRegistryCenter zkRegCenter = new com.dangdang
            .ddframe.reg.zookeeper.ZookeeperRegistryCenter(zkConfig);
    return zkRegCenter;
  }

  /**
   * 获取JobConfiguration的配置信息.
   *
   * @param jobName            作业名称
   * @param jobClass           作业实现类名称
   * @param shardingTotalCount 作业分片总数
   * @param cron               作业启动时间的cron表达式
   * @param jobParameter       作业自定义参数
   */
  public static JobConfiguration getJobConfig(String jobName, Class<? extends com.dangdang
          .ddframe.job.api.ElasticJob> jobClass, int shardingTotalCount, String cron, String
          jobParameter) {
    JobConfiguration jobConfig = new JobConfiguration(jobName, jobClass, shardingTotalCount, cron);
    jobConfig.setJobParameter(jobParameter);
    return jobConfig;
  }

  /**
   * 从注册表中获取已经添加进调度的任务.
   */
  public static JobScheduleController getJobScheduler(String jobName) {
    if (org.springframework.util.StringUtils.isEmpty(jobName)) {
      return null;
    }
    return JobRegistry.getInstance().getJobScheduleController(jobName);
  }

}
