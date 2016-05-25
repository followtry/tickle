/**
 * 
 */
package cn.jingzz.brief.service.scheduler.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

import org.quartz.CronExpression;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.dangdang.ddframe.job.api.JobConfiguration;
import com.dangdang.ddframe.job.api.JobScheduler;
import com.dangdang.ddframe.job.internal.schedule.JobScheduleController;
import com.dangdang.ddframe.reg.zookeeper.ZookeeperRegistryCenter;

import cn.jingzz.brief.service.scheduler.SchedulerService;
import cn.jingzz.brief.service.scheduler.base.ElasticJobSchedulerManager;
import cn.jingzz.brief.service.scheduler.base.SchedulerManager;
import cn.jingzz.brief.service.scheduler.bean.ScheduleJob;
import cn.jingzz.brief.service.scheduler.core.job.BatchThroughputDataFlowJob;
import cn.jingzz.brief.service.scheduler.core.job.JobListener;
import cn.jingzz.brief.service.scheduler.core.job.MyJobScheduler;
import cn.jingzz.brief.service.scheduler.core.job.QuartzJobFactory;
import cn.jingzz.brief.service.scheduler.core.job.SimpleJob;

/**
 * @author jingzz
 * @time 2016年4月26日 上午9:02:16
 * @name service-scheduler-impl/com.yonyou.worktime.service.scheduler.impl.
 *       SchedulerServiceImpl
 * @since 2016年4月26日 上午9:02:16
 */
public class SchedulerServiceImpl implements SchedulerService {

	/**  */
	private static final String WORKTIME_APPLICATIONCONTEXT_KEY = "worktime-application-context";

	private static final Logger LOG = LoggerFactory.getLogger(SchedulerServiceImpl.class);

	public void work(JobDataMap jobData, Date triggerTime, TriggerKey triggerKey)
			throws ParseException, SchedulerException {
		/*
		 * String cronExp = CronUtil.parseDate2CronExp(triggerTime);
		 * CronScheduleBuilder cronScheduleBuilder =
		 * CronScheduleBuilder.cronSchedule(cronExp); Calendar instance =
		 * Calendar.getInstance(); instance.setTime(triggerTime);
		 * instance.set(Calendar.MINUTE, instance.get(Calendar.MINUTE) + 1);
		 * Date triggerEndTime = instance.getTime(); startJobSchedule(jobData,
		 * cronScheduleBuilder, triggerKey, triggerEndTime);
		 */

		SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withRepeatCount(1);
		Trigger trigger = SchedulerManager.getNewSimpleTrigger(simpleScheduleBuilder, triggerKey, jobData, triggerTime);
		startJobScheduler(trigger);

	}

	public void work(ScheduleJob scheduleJob) throws ParseException {
		CronExpression.validateExpression(scheduleJob.getCronExpression());
		CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression());
		TriggerKey triggerKey = new TriggerKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
		startCronJobSchedule(scheduleJob.getJobDataMap(), cronScheduleBuilder, triggerKey,
				scheduleJob.getTriggerEndTime());
	}

	/**
	 * 
	 * @author jingzz
	 * @param jobData
	 * @param cronScheduleBuilder
	 * @param identityName
	 * @param identityGroup
	 * @param triggerEndTime
	 */
	private void startCronJobSchedule(JobDataMap jobData, CronScheduleBuilder scheduleBuilder, TriggerKey triggerKey,
			Date triggerEndTime) {
		Trigger newTrigger = SchedulerManager.getNewCronTrigger(scheduleBuilder, triggerKey, jobData, triggerEndTime);
		startJobScheduler(newTrigger);
	}

	/**
	 * @author jingzz
	 * @param newTrigger
	 */
	private void startJobScheduler(Trigger newTrigger) {
		try {
			JobDetail jobDetail = SchedulerManager.getNewJobDetai(QuartzJobFactory.class);
			Date firstFireTime = SchedulerManager.startJobScheduler(jobDetail, newTrigger);
			if (firstFireTime == null) {
				System.out.println("调度失败");
				LOG.error("调度失败！");
			} else {
				LOG.info("开始调度时间：" + firstFireTime);
				System.out.println("调度时间：" + DateFormat.getInstance().format(firstFireTime));
			}
		} catch (SchedulerException e) {
			LOG.error("Scheduler error:" + e);
		}
	}

	/**
	 * 调度任务
	 */
	public void scheduleThroughputDataFlowJob(ScheduleJob sJob) {
		verifyNull(sJob);
		String jobParams = JSON.toJSONString(sJob);
		ZookeeperRegistryCenter zkRegCenter = ElasticJobSchedulerManager.getZkRegCenter();
		JobConfiguration jobConfig = ElasticJobSchedulerManager.getJobConfig(sJob.getJobName(), BatchThroughputDataFlowJob.class,
				sJob.getShardingTotalCount(), sJob.getCronExpression(), jobParams);
		zkRegCenter.init();

		JobScheduler jobScheduler = new MyJobScheduler(zkRegCenter, jobConfig);
		jobScheduler.init();

	}

	@Override
	public void scheduleSimpleJob(ScheduleJob scheduleJob) {
		verifyNull(scheduleJob);
		String jobParams = JSON.toJSONString(scheduleJob.getJobClass());
		ZookeeperRegistryCenter zkRegCenter = ElasticJobSchedulerManager.getZkRegCenter();

		JobConfiguration jobConfig = ElasticJobSchedulerManager.getJobConfig(scheduleJob.getJobName(),
				SimpleJob.class, scheduleJob.getShardingTotalCount(), scheduleJob.getCronExpression(),
				jobParams);

		//连接到ZooKeeper注册中心
		zkRegCenter.init();

		JobScheduler jobScheduler = new MyJobScheduler(zkRegCenter, jobConfig, new JobListener(-1, -1));
		//调度任务并添加到任务注册表JobRegistry中
		jobScheduler.init();
		
		
	}


	/**
	 * 重新调度任务
	 * 
	 * @author jingzz
	 * @param jobScheduler
	 * @param cronExpression
	 */
	@Override
	public void reScheduler(JobScheduleController jobScheduler, String cronExpression) {
		System.out.println("SchedulerServiceImpl.reScheduler():任务已经被重新调度");
		jobScheduler.rescheduleJob(cronExpression);
	}

	/**
	 * @author jingzz
	 * @param sJob
	 */
	private void verifyNull(ScheduleJob sJob) {
		if (sJob == null) {
			throw new NullPointerException();
		}
	}
}
