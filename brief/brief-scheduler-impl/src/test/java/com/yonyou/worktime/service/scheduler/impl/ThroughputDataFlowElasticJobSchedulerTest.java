/**
 * 
 */
package com.yonyou.worktime.service.scheduler.impl;

import java.util.Date;

import com.alibaba.fastjson.JSON;

import cn.followtry.service.scheduler.SchedulerService;
import cn.followtry.service.scheduler.bean.ScheduleJob;
import cn.followtry.service.scheduler.core.job.BatchThroughputDataFlowJob;
import cn.followtry.service.scheduler.impl.SchedulerServiceImpl;
import cn.followtry.service.scheduler.util.CronUtil;

/**
 * @author jingzz
 * @time 2016年5月4日 上午9:53:16
 * @name service-scheduler-impl/com.yonyou.worktime.service.scheduler.impl.ElasticJobSchedulerTest
 * @since 2016年5月4日 上午9:53:16
 */
public class ThroughputDataFlowElasticJobSchedulerTest {

	public static void main(String[] args) {
		SchedulerService schedulerService = new SchedulerServiceImpl();
		
		ScheduleJob scheduleJob = new ScheduleJob();
		scheduleJob.setJobName("test_elasticJob_scheduler"+System.currentTimeMillis());
		scheduleJob.setJobClass(BatchThroughputDataFlowJob.class);
		scheduleJob.setCronExpression(CronUtil.parseDate2CronExp(new Date(System.currentTimeMillis() + (1000 * 10))));
		System.out.println(scheduleJob.getCronExpression());
		scheduleJob.setShardingTotalCount(3);
		
		String jsonString = JSON.toJSONString(scheduleJob);
		System.out.println(jsonString);
		schedulerService.scheduleThroughputDataFlowJob(scheduleJob);
	}
}
