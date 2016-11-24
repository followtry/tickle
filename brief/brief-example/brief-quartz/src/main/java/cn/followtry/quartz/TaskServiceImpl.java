package cn.followtry.quartz;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService,ApplicationContextAware {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TaskServiceImpl.class);
	
	private static Scheduler scheduler;
	
	private Map<String,ScheduleTask> allTaskCache = new ConcurrentHashMap<String,ScheduleTask>();
	
	private ConcurrentHashMap<String,String> allTaskNameCache = new ConcurrentHashMap<String,String>();
	
	private ApplicationContext ac;
	
	public TaskServiceImpl() {
		
	}
	
	@Override
	public Map<String,ScheduleTask> getAllTask() {
		return  new HashMap<String,ScheduleTask>(allTaskCache);
	}

	@Override
	public List<String> getAllCron() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ScheduleTask getTaskById(String taskId) {
		ScheduleTask scheduleTask = new ScheduleTask();
		scheduleTask.setId("12323");
		return scheduleTask;
	}

	@Override
	public ScheduleTask addTask(ScheduleTask task) throws Exception {
		scheduler = new StdSchedulerFactory().getScheduler();
		JobDetail jobDetail = JobBuilder.newJob(ProxyJob.class).withIdentity(task.getId(), task.getGroup()).build();
		@SuppressWarnings("deprecation")
		CronTriggerImpl trigger = new CronTriggerImpl(task.getId(), task.getGroup(), task.getCron());
		trigger.setCronExpression(task.getCron());
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
		if (!allTaskCache.containsKey(task.getId())) {
			allTaskCache.put(task.getId(), task);
		}
		if (!allTaskNameCache.containsKey(task.getId())) {
			allTaskNameCache.put(task.getId(), task.getName());
		}
		return task;
	}
	
	/**
	 * 处理与spring的集成
	 * @param m 被{@link Scheduled}注解了的方法的反射类
	 * @return 调度任务模型
	 */
	private ScheduleTask createSchedulerTask(Method m){
		ScheduleTask task = new ScheduleTask();
		task.setCron(m.getAnnotation(Scheduled.class).cron());
		task.setId(UUIDUtil.getUUID());
		task.setTrigger(m.getName());
		task.setGroup(m.getDeclaringClass().getName());
		return task;
	}
	

	@Override
	public ScheduleTask addTask(String taskName, String taskClassName, String triggerName, String cron) throws Exception {
		ScheduleTask task = new ScheduleTask();
		task.setName(taskName);
		String id = UUIDUtil.getUUID();
		task.setId(id);
		task.setGroup(taskClassName);
		task.setTrigger(triggerName);
		task.setCron(cron);
		ScheduleTask newTask = addTask(task);
		return newTask;
	}

	@Override
	public ScheduleTask removeTaskbyId(String taskId) {
		return null;
	}

	@Override
	public void stopScheduler() throws SchedulerException {
		if (scheduler.isStarted()) {
			scheduler.shutdown();
		}
	}

	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		this.ac = context;
		for (String beanDefinitionName : ac.getBeanDefinitionNames()) {
			try {
				Class<?> bean = ac.getBean(beanDefinitionName).getClass();
				for (Method m : bean.getDeclaredMethods()) {
					if (m.isAnnotationPresent(Scheduled.class)) {
						ScheduleTask springTask = createSchedulerTask(m);
						addTask(springTask);
					}
				}
			} catch (Exception e) {
				LOGGER.error("获取bean任务异常",e);
			}
		}
	}

	@Override
	public ScheduleTask addTask(Method m) throws Exception {
		return createSchedulerTask(m);
	}
	

}
