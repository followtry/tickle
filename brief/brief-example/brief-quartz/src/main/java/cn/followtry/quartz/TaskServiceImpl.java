package cn.followtry.quartz;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements ITaskService {
	
	@Autowired
	private SchedulerFactoryBean schedulerFactory;
	
	private ConcurrentHashMap<String,ScheduleTask> allTask = new ConcurrentHashMap<String,ScheduleTask>();

	@Override
	public List<ScheduleTask> getAllTask() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getAllCron() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ScheduleTask getTaskById(String taskId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ScheduleTask addTask(ScheduleTask task) throws Exception {
		Scheduler scheduler = schedulerFactory.getScheduler();
		JobDetail jobDetail = JobBuilder.newJob(ProxyJob.class).withIdentity(task.getId(), task.getGroup()).build();
		Trigger trigger = TriggerBuilder.newTrigger().withIdentity(task.getId(), task.getTrigger()).build();
		
		Object target = Class.forName(task.getGroup()).newInstance();
		Method method = target.getClass().getMethod(task.getTrigger());
		JobDataMap dataMap = trigger.getJobDataMap();
		dataMap.put(ProxyJob.DATA_TARGET_KEY, target);
		dataMap.put(ProxyJob.DATA_TASK_KEY, task);
		dataMap.put(ProxyJob.DATA_TRIGGER_KEY, method);
		dataMap.put(ProxyJob.DATA_TRIGGER_PARAMS_KEY, new Object[]{});
		scheduler.scheduleJob(jobDetail, trigger);
		if (!scheduler.isShutdown()) {
			scheduler.start();
		}
		if (!allTask.containsKey(task.getId())) {
			allTask.put(task.getId(), task);
		}
		return task;
	}
	
	private ScheduleTask createSchedulerTask(Method m){
		
		return null;
	}

	@Override
	public ScheduleTask addTask(String taskName, String taskClassName, String triggerName, String cron) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
