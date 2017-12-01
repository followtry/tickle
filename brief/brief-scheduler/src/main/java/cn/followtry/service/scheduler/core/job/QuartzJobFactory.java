package cn.followtry.service.scheduler.core.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**.
 * @author jingzz
 * @since 2016年4月26日 上午11:11:30
 */
public class QuartzJobFactory implements Job {

  /** . */

  @Override
  public void execute(JobExecutionContext context) throws JobExecutionException {
    System.out.println("JobScheduler.execute()");
    String key = "scheduleJob";
    context.getMergedJobDataMap().get(key);
  }


}
