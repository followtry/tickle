package cn.followtry.service.scheduler.core.job;

import cn.followtry.service.scheduler.bean.ScheduleJob;
import com.dangdang.ddframe.job.api.JobExecutionMultipleShardingContext;
import com.dangdang.ddframe.job.plugin.job.type.dataflow.AbstractIndividualThroughputDataFlowElasticJob;
import java.util.List;


/**
 * 高吞吐量逐条处理数据流程的作业.
 *
 * @author jingzz
 * @since 2016年5月18日 下午4:28:35
 */
public class IndividualThroughputDataFlowJob extends AbstractIndividualThroughputDataFlowElasticJob<ScheduleJob> {

  @Override
  public boolean processData(JobExecutionMultipleShardingContext shardingContext,ScheduleJob data) {
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
