/**
 * 
 */
package cn.jingzz.brief.service.scheduler.impl;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author jingzz
 * @time 2016年4月26日 上午11:11:30
 * @name service-scheduler-impl/com.yonyou.worktime.service.scheduler.impl.JobScheduler
 * @since 2016年4月26日 上午11:11:30
 */
public class QuartzJobFactory implements Job {

	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println("JobScheduler.execute()");
		String key ="scheduleJob";
		context.getMergedJobDataMap().get(key);
	}
	

}
