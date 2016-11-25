package cn.followtry.quartz;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.quartz.SchedulerException;

public interface TaskService {
	
	Map<String, ScheduleTask> getAllTask();
	
	List<String> getAllCron();
	
	ScheduleTask getTaskById(String taskId);
	
	/**添加任务<br>
	 * @author jingzz
	 * @param task
	 * @return
	 * @throws Exception
	 */
	ScheduleTask addTask(ScheduleTask task) throws Exception;
	
	/**
	 * 添加被{@link Scheduled}注解了的方法作为调度任务<br>
	 * @param m
	 * @return
	 * @throws Exception
	 */
	ScheduleTask addTask(Method m) throws Exception;
	
	/**
	 * 添加任务
	 * @author jingzz
	 * @param taskName
	 * @param taskClassName
	 * @param triggerName
	 * @param cron
	 * @return
	 * @throws Exception
	 */
	ScheduleTask addTask(String taskName,String taskClassName,String triggerName,String cron) throws Exception;
	
	ScheduleTask removeTaskbyId(String taskId);
	
	void stopScheduler() throws SchedulerException;
}
