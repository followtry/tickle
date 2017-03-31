/**
 * 
 */
package cn.followtry.service.scheduler.base;

import java.util.Date;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.util.CollectionUtils;

/**
 * @author jingzz
 * @time 2016年4月26日 上午10:48:39
 * @name service-scheduler-impl/com.yonyou.worktime.service.scheduler.base.SchedulerManager
 * @since 2016年4月26日 上午10:48:39
 */
public class SchedulerManager {
	
	private static Trigger trigger;
	
	private static Scheduler scheduler;
	
	/**
	 * 获取Scheduler单例
	 * @author jingzz
	 * @return
	 * @throws SchedulerException
	 */
	public static synchronized Scheduler getSchedulerInstance() throws SchedulerException{
		if (scheduler == null) {
			scheduler = getDefaultScheduler();
		}
		return scheduler;
	}
	
	public static Trigger getNewCronTrigger(CronScheduleBuilder cronScheduleBuilder,TriggerKey triggerKey ,JobDataMap jobData,Date triggerEndTime){
		if (cronScheduleBuilder == null) {
			return null;
		}
		if (CollectionUtils.isEmpty(jobData)) {
			jobData = new JobDataMap();
		}
		
		trigger = TriggerBuilder.newTrigger()
								.withIdentity(triggerKey)
								.withSchedule(cronScheduleBuilder)
								.usingJobData(jobData)
								.endAt(triggerEndTime)
								.build();
		return trigger;
	}
	
	/**
	 * 定时触发器
	 * @author jingzz
	 * @param simpleScheduleBuilder
	 * @param triggerKey
	 * @param jobDataMap
	 * @param triggerTime
	 * @return
	 */
	public static Trigger getNewSimpleTrigger(SimpleScheduleBuilder simpleScheduleBuilder,TriggerKey triggerKey,JobDataMap jobDataMap,Date triggerTime){
		if (simpleScheduleBuilder == null) {
			return null;
		}
		if (CollectionUtils.isEmpty(jobDataMap)) {
			jobDataMap = new JobDataMap();
		}
		
		trigger = TriggerBuilder.newTrigger()
								.startAt(triggerTime)
								.withIdentity(triggerKey)
								.withSchedule(simpleScheduleBuilder)
								.usingJobData(jobDataMap)
								.build();
		return trigger;
	}
	
	public static Scheduler getDefaultScheduler() throws SchedulerException{
		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
		return scheduler;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static JobDetail getNewJobDetai(Class clazz) throws SchedulerException{
		if (clazz == null) {
			return null;
		}
		JobKey jobKey = new JobKey(clazz.getSimpleName(), clazz.getPackage().getName());
		
		JobDetail jobDetail = JobBuilder.newJob(clazz)
										.withIdentity(jobKey)
										.requestRecovery(true)
										.storeDurably(true)
										.withDescription(null)
										.build();
		return jobDetail;
	}
	
	/**
	 * 开始调度任务
	 * @author jingzz
	 * @param scheduler
	 * @param jobDetail
	 * @param trigger
	 * @return 第一次任务触发时间，scheduler为null则返回null
	 * @throws SchedulerException
	 */
	public static Date startJobScheduler(JobDetail jobDetail,Trigger trigger) throws SchedulerException{
		Scheduler scheduler = getSchedulerInstance();
		scheduler.start();
		Date firstFireTime = scheduler.scheduleJob(jobDetail, trigger);
		return firstFireTime;
	} 
	
	/**关闭调度
	 * @author jingzz
	 * @throws SchedulerException
	 */
	public static void stopJobScheduler() throws SchedulerException{
		getSchedulerInstance().shutdown(true);
	} 
	
	/**
	 * 将job从Scheduler中移除
	 * @author jingzz
	 * @param jobKey
	 * @return true:job存在并成功删除；false:其他情况
	 * @throws SchedulerException
	 */
	public static boolean removeJobFromScheduler(JobKey jobKey) throws SchedulerException{
		return getSchedulerInstance().deleteJob(jobKey);
	}
	
	/**
	 * 更新job触发调度器，并重新调度job
	 * @author jingzz
	 * @param triggerKey
	 * @param newTrigger
	 * @return
	 * @throws SchedulerException
	 */
	public static Date rescheduleJob(TriggerKey triggerKey, Trigger newTrigger) throws SchedulerException{
		Date firstFireTime = getSchedulerInstance().rescheduleJob(triggerKey, newTrigger);
		return firstFireTime;
	}
	
}
