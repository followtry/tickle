package cn.followtry.service.scheduler;

import cn.followtry.service.scheduler.bean.ScheduleJob;
import com.dangdang.ddframe.job.internal.schedule.JobScheduleController;
import java.text.ParseException;
import java.util.Date;
import org.quartz.JobDataMap;
import org.quartz.SchedulerException;
import org.quartz.TriggerKey;

/**
 * .
 *
 * @author jingzz
 * @since 2016年4月26日 上午8:58:32
 */
public interface SchedulerService {


  /**
   * 循环调度任务.
   */
  void work(ScheduleJob scheduleJob) throws ParseException;

  /**
   * 定时触发调度（仅一次）任务.
   *
   * @param jobData     要传递的数据
   * @param triggerTime 触发时间
   * @param triggerKey  触发器唯一key
   */


  void work(JobDataMap jobData,Date triggerTime,TriggerKey triggerKey) throws ParseException,
          SchedulerException;

  /**
   * 调度任务.
   */
  void scheduleThroughputDataFlowJob(ScheduleJob scheduleJob);

  /**
   * .
   */
  void scheduleSimpleJob(ScheduleJob scheduleJob);

  /**
   * 重新调度任务.
   */
  void reScheduler(JobScheduleController jobScheduler,String cronExpression);
}
