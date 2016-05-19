/**
 * 
 */
package cn.jingzz.brief.service.scheduler.core.job;

import com.dangdang.ddframe.job.api.JobExecutionMultipleShardingContext;
import com.dangdang.ddframe.job.api.listener.AbstractDistributeOnceElasticJobListener;

/**
 * @author jingzz
 * @time 2016年5月19日 上午9:57:39
 * @name brief-scheduler-impl/cn.jingzz.brief.service.scheduler.core.job.JobListener
 * @since 2016年5月19日 上午9:57:39
 */
public class JobListener extends AbstractDistributeOnceElasticJobListener {

	/**
	 * @param startedTimeoutMilliseconds
	 * @param completedTimeoutMilliseconds
	 */
	public JobListener(long startedTimeoutMilliseconds, long completedTimeoutMilliseconds) {
		super(startedTimeoutMilliseconds, completedTimeoutMilliseconds);
	}

	@Override
	public void doBeforeJobExecutedAtLastStarted(JobExecutionMultipleShardingContext shardingContext) {
		System.out.println("JobListener.doBeforeJobExecutedAtLastStarted():在任务执行前执行监听器操作");
	}

	@Override
	public void doAfterJobExecutedAtLastCompleted(JobExecutionMultipleShardingContext shardingContext) {
		System.out.println("JobListener.doBeforeJobExecutedAtLastStarted():在任务执行后执行监听器操作");

	}

}
