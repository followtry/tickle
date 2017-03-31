/**
 * 
 */
package cn.followtry.service.scheduler.core.job;

import java.util.Properties;

import com.dangdang.ddframe.job.api.JobConfiguration;
import com.dangdang.ddframe.job.api.JobScheduler;
import com.dangdang.ddframe.job.api.listener.ElasticJobListener;
import com.dangdang.ddframe.reg.base.CoordinatorRegistryCenter;

/**
 * @author jingzz
 * @time 2016年5月19日 上午10:10:36
 * @name brief-scheduler-impl/cn.jingzz.brief.service.scheduler.core.job.MyJobScheduler
 * @since 2016年5月19日 上午10:10:36
 */
public class MyJobScheduler extends JobScheduler {

	/**
	 * @param regCenter
	 * @param jobConfig
	 * @param elasticJobListeners
	 */
	public MyJobScheduler(CoordinatorRegistryCenter regCenter, JobConfiguration jobConfig,
			ElasticJobListener... elasticJobListeners) {
		super(regCenter, jobConfig, elasticJobListeners);
	}
	
	@Override
	protected void prepareEnvironments(Properties props) {
		super.prepareEnvironments(props);
		System.out.println("MyJobScheduler.prepareEnvironments()：准备调度环境");
		
	}

}
