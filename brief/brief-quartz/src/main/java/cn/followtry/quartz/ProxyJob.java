package cn.followtry.quartz;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

public class ProxyJob implements Job {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProxyJob.class);
	
	public static final String DATA_TARGET_KEY = "target_key";
	
	public static final String DATA_TRIGGER_KEY = "trigger_key";
	
	public static final String DATA_TRIGGER_PARAMS_KEY = "trigger_params_key";
	
	public static final String DATA_TASK_KEY = "task_key";
	
	//注入的属性，与设置JobDataMap是的key同名
	private Object  target_key;
	
	private ScheduleTask  task_key;
	
	private Method  trigger_key;
	
	private Object[]  trigger_params_key;
	
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
		String taskJson = JSON.toJSONString(task_key);
		LOGGER.info("通过注入方式获取到执行的任务:{}",taskJson);
		System.out.println(taskJson);
		System.out.println(JSON.toJSONString(task_key));
		try {
			trigger_key.invoke(target_key, trigger_params_key);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	public void setTarget_key(Object target_key) {
		this.target_key = target_key;
	}

	public void setTask_key(ScheduleTask task_key) {
		this.task_key = task_key;
	}

	public void setTrigger_key(Method trigger_key) {
		this.trigger_key = trigger_key;
	}

	public void setTrigger_params_key(Object[] trigger_params_key) {
		this.trigger_params_key = trigger_params_key;
	}

}
