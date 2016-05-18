/**
 * 
 */
package cn.jingzz.brief.service.scheduler;

import java.text.ParseException;
import java.util.Date;

import org.quartz.JobDataMap;
import org.quartz.SchedulerException;
import org.quartz.TriggerKey;

import com.yonyou.worktime.base.stereotype.validation.NotNull;

import cn.jingzz.brief.service.scheduler.bean.ScheduleJob;

/**
 * @author jingzz
 * @time 2016年4月26日 上午8:58:32
 * @name service-scheduler/com.yonyou.worktime.service.scheduler.SchedulerService
 * @since 2016年4月26日 上午8:58:32
 */
public interface SchedulerService {

	
	/**
	 * 循环调度任务
	 * @author jingzz
	 * @param scheduleJob
	 * @throws ParseException
	 */
	void work(@NotNull(name="scheduleJob") ScheduleJob scheduleJob) throws ParseException;

	/**
	 * 定时触发调度（仅一次）任务
	 * @author jingzz
	 * @param jobData 要传递的数据
	 * @param triggerTime 触发时间
	 * @param triggerKey 触发器唯一key
	 * @throws ParseException 
	 * @throws SchedulerException 
	 */
	void work(JobDataMap jobData,@NotNull(name="triggerTime") Date triggerTime, @NotNull(name="triggerKey") TriggerKey triggerKey) throws ParseException, SchedulerException;
	
	/**
	 * 调度任务
	 * @author jingzz
	 * @param scheduleJob
	 */
	void scheduleJob(ScheduleJob scheduleJob);
}
