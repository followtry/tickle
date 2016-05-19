/**
 * 
 */
package cn.jingzz.brief.service.scheduler.core.job;

import java.time.LocalDateTime;
import java.time.ZoneId;

import com.dangdang.ddframe.job.api.JobExecutionMultipleShardingContext;
import com.dangdang.ddframe.job.plugin.job.type.simple.AbstractSimpleElasticJob;

/**
 * @author jingzz
 * @time 2016年5月18日 下午3:31:01
 * @name brief-scheduler-impl/cn.jingzz.brief.service.scheduler.impl.SimpleJob
 * @since 2016年5月18日 下午3:31:01
 */
public class SimpleJob extends AbstractSimpleElasticJob {

	@Override
	public void process(JobExecutionMultipleShardingContext shardingContext) {
		shardingContext.createJobExecutionSingleShardingContext(2);
		System.out.println("SimpleJob.process():"+LocalDateTime.now(ZoneId.of(ZoneId.SHORT_IDS.get("CTT"))));
	}

}
