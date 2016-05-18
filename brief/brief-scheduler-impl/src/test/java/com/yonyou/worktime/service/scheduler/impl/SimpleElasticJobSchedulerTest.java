/**
 * 
 */
package com.yonyou.worktime.service.scheduler.impl;

import com.alibaba.fastjson.JSON;

import cn.jingzz.brief.service.scheduler.SchedulerService;
import cn.jingzz.brief.service.scheduler.bean.ScheduleJob;
import cn.jingzz.brief.service.scheduler.core.job.SimpleJob;
import cn.jingzz.brief.service.scheduler.impl.SchedulerServiceImpl;

/**
 * @author jingzz
 * @time 2016年5月18日 下午3:38:01
 * @name brief-scheduler-impl/com.yonyou.worktime.service.scheduler.impl.
 *       SimpleElasticJobSchedulerTest
 * @since 2016年5月18日 下午3:38:01
 */
public class SimpleElasticJobSchedulerTest {

	public static void main(String[] args) {

		SchedulerService schedulerService = new SchedulerServiceImpl();

		/*
		 * 构建定时调度参数对象
		 */

		ScheduleJob scheduleJob = new ScheduleJob();
		scheduleJob.setJobName("SimpleElasticJobSchedulerTest" + System.currentTimeMillis());
		scheduleJob.setJobClass(SimpleJob.class);
		scheduleJob.setCronExpression("0/5 * * * * ?");
		System.out.println(scheduleJob.getCronExpression());
		scheduleJob.setShardingTotalCount(3);
		System.out.println(JSON.toJSONString(scheduleJob));
		
		schedulerService.scheduleSimpleJob(scheduleJob);
		
	}

}
