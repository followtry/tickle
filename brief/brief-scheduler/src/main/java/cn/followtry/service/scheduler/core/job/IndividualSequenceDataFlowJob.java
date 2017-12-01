package cn.followtry.service.scheduler.core.job;

import cn.followtry.service.scheduler.bean.ScheduleJob;
import com.dangdang.ddframe.job.api.JobExecutionSingleShardingContext;
import com.dangdang.ddframe.job.plugin.job.type.dataflow.AbstractIndividualSequenceDataFlowElasticJob;
import java.util.List;

/**
 * 保证同一分片顺序性的逐条处理数据流程的作业.
 *
 * @author jingzz
 * @since 2016年5月18日 下午4:31:05
 */
public class IndividualSequenceDataFlowJob extends AbstractIndividualSequenceDataFlowElasticJob<ScheduleJob> {


  @Override
  public boolean processData(JobExecutionSingleShardingContext shardingContext,ScheduleJob data) {
    // TODO Auto-generated method stub
    return false;
  }


  @Override
  public List<ScheduleJob> fetchData(JobExecutionSingleShardingContext shardingContext) {
    // TODO Auto-generated method stub
    return null;
  }


  @Override
  public boolean isStreamingProcess() {
    // TODO Auto-generated method stub
    return false;
  }

}
