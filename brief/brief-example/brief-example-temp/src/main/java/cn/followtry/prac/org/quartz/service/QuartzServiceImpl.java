/**
 * 
 */
package cn.followtry.prac.org.quartz.service;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * quartz调度服务
 * @author jingzz
 * @time 2016年4月21日 下午2:10:56
 * @name brief-service/cn.jingzztech.prac.quartz.QuartzServiceImpl
 * @since 2016年4月21日 下午2:10:56
 */
public class QuartzServiceImpl implements Job{
	
	private static final Logger LOG = LoggerFactory.getLogger(QuartzServiceImpl.class);

	@Override
	
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		JobDetail jobDetail = context.getJobDetail();
		JobKey key = jobDetail.getKey();
		String name = key.getName();
		String group = key.getGroup();
		LOG.info("调度任务：name="+name+",group="+group);
		
		JobDataMap jobDataMap = jobDetail.getJobDataMap();
		String dirName = jobDataMap.getString("SCAN_DIR");
	}
	
}
