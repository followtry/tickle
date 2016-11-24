package cn.followtry.quartz;

import java.util.List;
import java.util.Map;

import org.quartz.SchedulerException;

public interface TaskService {
	
	Map<String, ScheduleTask> getAllTask();
	
	List<String> getAllCron();
	
	ScheduleTask getTaskById(String taskId);
	
	ScheduleTask addTask(ScheduleTask task) throws Exception;
	
	ScheduleTask addTask(String taskName,String taskClassName,String triggerName,String cron) throws Exception;
	
	ScheduleTask removeTaskbyId(String taskId);
	
	void stopScheduler() throws SchedulerException;
}
