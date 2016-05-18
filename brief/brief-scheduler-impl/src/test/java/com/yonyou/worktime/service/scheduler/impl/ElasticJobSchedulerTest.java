/**
 * 
 */
package com.yonyou.worktime.service.scheduler.impl;

import java.util.Date;

import com.alibaba.fastjson.JSON;

import cn.jingzz.brief.service.scheduler.base.SchedulerManager;
import cn.jingzz.brief.service.scheduler.impl.SchedulerServiceImpl;
import cn.jingzz.brief.service.scheduler.util.CronUtil;

/**
 * @author jingzz
 * @time 2016年5月4日 上午9:53:16
 * @name service-scheduler-impl/com.yonyou.worktime.service.scheduler.impl.ElasticJobSchedulerTest
 * @since 2016年5月4日 上午9:53:16
 */
public class ElasticJobSchedulerTest {

	public static void main(String[] args) {
		SchedulerServiceImpl schedulerService = new SchedulerServiceImpl();
		
		SchedulerManager scheduleJob = new SchedulerManager();
		scheduleJob.setJobName("test_elasticJob_scheduler"+System.currentTimeMillis());
//		scheduleJob.setJobClass(ThroughputDataFlowElasticJob.class);
		scheduleJob.setCronExpression(CronUtil.parseDate2CronExp(new Date(System.currentTimeMillis() + (1000 * 10))));
		System.out.println(scheduleJob.getCronExpression());
		scheduleJob.setShardingTotalCount(3);
		
		String jsonString = JSON.toJSONString(scheduleJob);
		System.out.println(jsonString);
		schedulerService.scheduleJob(scheduleJob);
		
	}
}
