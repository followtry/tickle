package cn.followtry.service.scheduler.core.job;

import com.dangdang.ddframe.job.api.JobConfiguration;
import com.dangdang.ddframe.job.api.JobScheduler;
import com.dangdang.ddframe.job.api.listener.ElasticJobListener;
import com.dangdang.ddframe.reg.base.CoordinatorRegistryCenter;
import java.util.Properties;

/**
 * .
 *
 * @author jingzz
 * @since 2016年5月19日 上午10:10:36
 */
public class MyJobScheduler extends JobScheduler {

  /**
   * .
   *
   * @param regCenter           注册中心
   * @param jobConfig           任务配置
   * @param elasticJobListeners 任务监听器
   */
  public MyJobScheduler(CoordinatorRegistryCenter regCenter,JobConfiguration jobConfig,
                        ElasticJobListener... elasticJobListeners) {
    super(regCenter,jobConfig,elasticJobListeners);
  }

  @Override
  protected void prepareEnvironments(Properties props) {
    super.prepareEnvironments(props);
    System.out.println("MyJobScheduler.prepareEnvironments()：准备调度环境");

  }

}
