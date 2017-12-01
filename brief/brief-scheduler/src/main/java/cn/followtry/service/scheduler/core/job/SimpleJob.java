package cn.followtry.service.scheduler.core.job;

import com.alibaba.fastjson.JSON;
import com.dangdang.ddframe.job.api.JobExecutionMultipleShardingContext;
import com.dangdang.ddframe.job.plugin.job.type.simple.AbstractSimpleElasticJob;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**.
 * @author jingzz
 * @since 2016年5月18日 下午3:31:01
 */
public class SimpleJob extends AbstractSimpleElasticJob {


  @Override
  @SuppressWarnings("rawtypes")
  public void process(JobExecutionMultipleShardingContext shardingContext) {
    shardingContext.createJobExecutionSingleShardingContext(2);
    String jobParameter = JSON.parseObject(shardingContext.getJobParameter(),String.class);
    Class clazz;
    try {
      clazz = Class.forName(jobParameter);
    } catch (ClassNotFoundException e) {
      clazz = null;
      e.printStackTrace();
    }
    try {
      clazz.newInstance();
    } catch (InstantiationException | IllegalAccessException e) {
      e.printStackTrace();
    }
    System.out.println("SimpleJob.process():" + LocalDateTime.now(ZoneId.of(ZoneId.SHORT_IDS.get("CTT"))) + ":" + clazz.getName());
  }

}
