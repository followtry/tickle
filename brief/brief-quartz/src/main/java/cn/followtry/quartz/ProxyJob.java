package cn.followtry.quartz;

import com.alibaba.fastjson.JSON;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ProxyJob implements Job {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProxyJob.class);
	
	public static final String DATA_TARGET_KEY = "targetKey";
	
	public static final String DATA_TRIGGER_KEY = "triggerKey";
	
	public static final String DATA_TRIGGER_PARAMS_KEY = "triggerParamsKey";
	
	public static final String DATA_TASK_KEY = "taskKey";
	
	//注入的属性，与设置JobDataMap是的key同名
	private Object targetKey;
	
	private ScheduleTask taskKey;
	
	private Method triggerKey;
	
	private Object[] triggerParamsKey;
	
    @Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		//原始方式获取jobDataMap中的数据
		executeViaRawType(context);
		
		//注入方式获取jobDataMap中的数据
		executeViaInjecting();
	}

	/**
	 * @author jingzz
	 * @param context
	 */
	private void executeViaRawType(JobExecutionContext context) {
		JobDataMap dataMap = context.getTrigger().getJobDataMap();
		Object target = dataMap.get(DATA_TARGET_KEY);
		ScheduleTask task = (ScheduleTask)dataMap.get(DATA_TASK_KEY);
		Method method = (Method)dataMap.get(DATA_TRIGGER_KEY);
		Object[] params = (Object[])dataMap.get(DATA_TRIGGER_PARAMS_KEY);
		
		String taskJson = JSON.toJSONString(task);
		LOGGER.info("通过原始方式获取到执行的任务:{}",taskJson);
		System.out.println(taskJson);
		try {
			method.invoke(target, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 通过注入方式获取JobDataMap中的数据，
	 * 即在当前类中的属性与JobDataMap中设置的相同即可通过Quartz框架自动注入
	 * @author jingzz
	 */
	private void executeViaInjecting() {
		String taskJson = JSON.toJSONString(taskKey);
		LOGGER.info("通过注入方式获取到执行的任务:{}",taskJson);
		System.out.println(taskJson);
		System.out.println(JSON.toJSONString(taskKey));
		try {
			triggerKey.invoke(targetKey, triggerParamsKey);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	public void setTargetKey(Object targetKey) {
		this.targetKey = targetKey;
	}

	public void setTaskKey(ScheduleTask taskKey) {
		this.taskKey = taskKey;
	}

	public void setTriggerKey(Method triggerKey) {
		this.triggerKey = triggerKey;
	}

	public void setTriggerParamsKey(Object[] triggerParamsKey) {
		this.triggerParamsKey = triggerParamsKey;
	}

}
