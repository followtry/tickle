package cn.followtry.service.scheduler.bean;

import java.util.Date;
import org.quartz.JobDataMap;

/**
 * 任务调度实体.
 *
 * @author jingzz
 * @since 2016年4月26日 下午2:30:52
 */
public class ScheduleJob {

  /**
   * Job的唯一Id.
   */
  private String jobId;

  /**
   * job名称.
   */
  private String jobName;

  /**
   * Job所属组.
   */
  private String jobGroup;

  /**
   * Job的状态.
   */
  private String jobStatus;

  /**
   * Job触发的cron表达式.
   */
  private String cronExpression;

  /**
   * Job描述信息.
   */
  private String desc;

  /**
   * Job携带的参数.
   */
  private JobDataMap jobDataMap;

  /**
   * Job结束时间.
   */
  private Date triggerEndTime;

  /**
   * 执行Job的类.
   */
  private Class<? extends Object> jobClass;

  /**
   * 分片总数.
   */
  private int shardingTotalCount;

  /**
   * 自定义参数.
   */
  private String jobParameter;

  public String getJobParameter() {
    return jobParameter;
  }

  public void setJobParameter(String jobParameter) {
    this.jobParameter = jobParameter;
  }

  public Class<? extends Object> getJobClass() {
    return jobClass;
  }

  public void setJobClass(Class<? extends Object> jobClass) {
    this.jobClass = jobClass;
  }

  public int getShardingTotalCount() {
    return shardingTotalCount;
  }

  public void setShardingTotalCount(int shardingTotalCount) {
    this.shardingTotalCount = shardingTotalCount;
  }

  /*public String getJobParameter() {
    return jobParameter;
  }

  public void setJobParameter(String jobParameter) {
    this.jobParameter = jobParameter;
  }
*/
  public Date getTriggerEndTime() {
    return triggerEndTime;
  }

  public void setTriggerEndTime(Date triggerEndTime) {
    this.triggerEndTime = triggerEndTime;
  }

  public JobDataMap getJobDataMap() {
    return jobDataMap;
  }

  public void setJobDataMap(JobDataMap jobDataMap) {
    this.jobDataMap = jobDataMap;
  }

  public String getJobId() {
    return jobId;
  }

  public void setJobId(String jobId) {
    this.jobId = jobId;
  }

  public String getJobName() {
    return jobName;
  }

  public void setJobName(String jobName) {
    this.jobName = jobName;
  }

  public String getJobGroup() {
    return jobGroup;
  }

  public void setJobGroup(String jobGroup) {
    this.jobGroup = jobGroup;
  }

  public String getJobStatus() {
    return jobStatus;
  }

  public void setJobStatus(String jobStatus) {
    this.jobStatus = jobStatus;
  }

  public String getCronExpression() {
    return cronExpression;
  }

  public void setCronExpression(String cronExpression) {
    this.cronExpression = cronExpression;
  }

  public String getDesc() {
    return desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }


  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("ScheduleJob{");
    sb.append("jobId='").append(jobId).append('\'');
    sb.append(", jobName='").append(jobName).append('\'');
    sb.append(", jobGroup='").append(jobGroup).append('\'');
    sb.append(", jobStatus='").append(jobStatus).append('\'');
    sb.append(", cronExpression='").append(cronExpression).append('\'');
    sb.append(", desc='").append(desc).append('\'');
    sb.append(", jobDataMap=").append(jobDataMap);
    sb.append(", triggerEndTime=").append(triggerEndTime);
    sb.append(", jobClass=").append(jobClass);
    sb.append(", shardingTotalCount=").append(shardingTotalCount);
    sb.append(", jobParameter='").append(jobParameter).append('\'');
    sb.append('}');
    return sb.toString();
  }
}
