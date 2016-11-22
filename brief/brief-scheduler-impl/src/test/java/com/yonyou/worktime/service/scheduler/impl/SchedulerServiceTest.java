/**
 * 
 */
package com.yonyou.worktime.service.scheduler.impl;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import org.quartz.JobDataMap;
import org.quartz.SchedulerException;
import org.quartz.TriggerKey;

import cn.followtry.service.scheduler.SchedulerService;
import cn.followtry.service.scheduler.bean.ScheduleJob;
import cn.followtry.service.scheduler.impl.SchedulerServiceImpl;
import cn.followtry.service.scheduler.util.CronUtil;

/**
 * @author jingzz
 * @time 2016年4月26日 下午4:19:54
 * @name service-scheduler-impl/com.yonyou.worktime.service.scheduler.impl.SchedulerServiceTest
 * @since 2016年4月26日 下午4:19:54
 */
public class SchedulerServiceTest {
	
	public static void main(String[] args) throws ParseException, SchedulerException {
		SchedulerService schedulerService = new SchedulerServiceImpl();
		System.out.println("start!");
		Calendar instance = Calendar.getInstance();
		instance.set(2016, 4, 28, 18, 27, 10);
		
		JobDataMap jobData = new JobDataMap();
		jobData.put("jingzz", "heh");
		
		//1.调度方法
		TriggerKey triggerKey = new TriggerKey(UUID.randomUUID().toString(), null);
		schedulerService.work(jobData, instance.getTime(), triggerKey );
		
		
		//2.调度方法
		String cronExp = CronUtil.parseDate2CronExp(instance.getTime());
		ScheduleJob scheduleJob = new ScheduleJob();
		scheduleJob.setJobId("1234567890");
		scheduleJob.setJobName(UUID.randomUUID().toString());
		scheduleJob.setJobGroup(null);
		scheduleJob.setCronExpression(cronExp);
		scheduleJob.setTriggerEndTime(new Date(System.currentTimeMillis()+ (1000 * 60 * 60 * 24 * 60)));
		schedulerService.work(scheduleJob);
		
		//TODO bug 时间冲突时只调用一次
		
		System.out.println("end!");
	}
}
