/**
 * 
 */
package cn.jingzz.brief.service.scheduler.core.job;

import java.util.List;

import com.dangdang.ddframe.job.api.JobExecutionMultipleShardingContext;
import com.dangdang.ddframe.job.plugin.job.type.dataflow.AbstractIndividualThroughputDataFlowElasticJob;

import cn.jingzz.brief.service.scheduler.bean.ScheduleJob;

/**
 * 高吞吐量逐条处理数据流程的作业.
 * @author jingzz
 * @time 2016年5月18日 下午4:28:35
 * @name brief-scheduler-impl/cn.jingzz.brief.service.scheduler.impl.IndividualThroughputDataFlowJob
 * @since 2016年5月18日 下午4:28:35
 */
public class IndividualThroughputDataFlowJob extends AbstractIndividualThroughputDataFlowElasticJob<ScheduleJob> {

	@Override
	public boolean processData(JobExecutionMultipleShardingContext shardingContext, ScheduleJob data) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<ScheduleJob> fetchData(JobExecutionMultipleShardingContext shardingContext) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isStreamingProcess() {
		// TODO Auto-generated method stub
		return false;
	}

}
