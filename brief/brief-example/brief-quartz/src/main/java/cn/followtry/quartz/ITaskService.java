package cn.followtry.quartz;

import java.util.List;

public interface ITaskService {
	
	List<ScheduleTask> getAllTask();
	
	List<String> getAllCron();
	
	ScheduleTask getTaskById(String taskId);
	
	ScheduleTask addTask(ScheduleTask task) throws Exception;
	
	ScheduleTask addTask(String taskName,String taskClassName,String triggerName,String cron);
}
