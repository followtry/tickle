/**
 * 
 */
package cn.followtry.incubate.org.quartz;

import java.text.ParseException;
import java.util.Date;

import org.quartz.CronScheduleBuilder;
import org.quartz.DateBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.calendar.CronCalendar;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.followtry.incubate.org.quartz.service.QuartzServiceImpl;

/**
 * @author jingzz
 * @time 2016年4月21日 下午3:08:21
 * @name brief-service/cn.jingzztech.prac.quartz.QuartzServiceTest
 * @since 2016年4月21日 下午3:08:21
 */
public class QuartzServiceTest {
	
	private static final Logger LOG = LoggerFactory.getLogger(QuartzServiceTest.class);
	
	private static String group = "group1";
	
	//定义任务
	private static String name = QuartzServiceImpl.class.getSimpleName();
	
	public static void main(String[] args) throws SchedulerException, ParseException {
		
		//创建默认调度器
		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
		scheduler.start();
		
		/*
		 * 添加任务,addJob
		 * 检查任务,checkExists
		 * 删除任务，deleteJob
		 * 获取任务，getJobDetail
		 * 中断任务，interrupt
		 * 暂停任务，pauseJob
		 * 恢复任务，resumeJob
		 * 调度任务，scheduleJob
		 * 触发任务，triggerJob
		 * 接触调度任务，unscheduleJob
		 */
		
		startUsingQuartz(scheduler);
		
		/*
		 * Quartz 接口
		 * 
		 *  Scheduler:调度交互的主要接口
		 *  Job：被调度执行的组件需要实现该接口
		 *  JobDetail：定义job示例
		 *  Trigger ：触发器，定义调度上的任务被执行条件
		 *  JobBuilder ：用来定义或构建JobDetail实例。
		 *  TriggerBuilder ：用于定义trigger实例
		 *   
		 */
		
		/*
		 * scheduler只有在调用start()方法后才会由触发器触发任务执行
		 * JobKey 和 TriggerKey是name和group的混合体，不能重复
		 */
		JobKey jobKey = new JobKey(name, group);
		
		JobDataMap jdm = new JobDataMap();
		
		String jobDescription = "1234567890";
		JobDetail job = JobBuilder.newJob(QuartzServiceImpl.class)
				.withIdentity(jobKey.getName(), jobKey.getGroup())
				.usingJobData("jingzz", true)
				.usingJobData("hehe", "xixi")
				.storeDurably(true)
				.requestRecovery(true)
				.setJobData(jdm)
				.withDescription(jobDescription )
				.build();
		
		JobDataMap jobDataMap = job.getJobDataMap();
		boolean data = jobDataMap.getBoolean("jingzz");
		LOG.info("heheh:"+data);
		
		CronTriggerImpl cronTrigger = new CronTriggerImpl();
		
		CronCalendar cronCalendar = new CronCalendar("0/2 * * * * ?");
		
		int hour = 12;
		int minute = 23;
		int dayOfMonth = 12;
		CronScheduleBuilder.monthlyOnDayAndHourAndMinute(dayOfMonth , hour, minute);
		
		int d = SimpleTrigger.REPEAT_INDEFINITELY;
		
		Date date = null;
		int secondBase = 0;
		DateBuilder.nextGivenSecondDate(date, secondBase);
		
		String jobName = System.currentTimeMillis()+"";
		String jobGroup = null;
		Date triggerStartTime = null;
		Date triggerEndTime = null;
		int intervalInSeconds = 0;
		int triggerRepeatCount = 0;
		TriggerBuilder.newTrigger()
						.forJob(jobName, jobGroup)
						.startAt(triggerStartTime)
						.withIdentity(jobName)
						.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(intervalInSeconds)
								.withRepeatCount(triggerRepeatCount)
								.repeatForever())
						.endAt(triggerEndTime);
		
		
		
	}

	/**
	 * @author jingzz
	 * @param scheduler
	 * @throws SchedulerException
	 */
	private static void startUsingQuartz(Scheduler scheduler) throws SchedulerException {
		JobKey jobKey = new JobKey(name, group);
		JobDetail job = JobBuilder.newJob(QuartzServiceImpl.class)
									.withIdentity(jobKey.getName(), jobKey.getGroup()).build();
		
		//定义触发器
		Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", group)
									.startNow()
									.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(3).repeatForever())
									.usingJobData("jingzz", true)
									.usingJobData("hehe", "xixi")
									.build();
		
		//告诉scheduler使用触发器调度任务
		scheduler.scheduleJob(job, trigger);
		
		
		
	}
	
	class Hello{
		
		private String name;
		
		private String id;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}
	}
}
