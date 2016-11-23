package cn.followtry.quartz;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.alibaba.fastjson.JSON;

public class ProxyJob implements Job {
	
	public static final String DATA_TARGET_KEY = "target_key";
	
	public static final String DATA_TRIGGER_KEY = "trigger_key";
	
	public static final String DATA_TRIGGER_PARAMS_KEY = "trigger_params_key";
	
	public static final String DATA_TASK_KEY = "task_key";
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		JobDataMap dataMap = context.getTrigger().getJobDataMap();
		Object target = dataMap.get(DATA_TARGET_KEY);
		ScheduleTask task = (ScheduleTask)dataMap.get(DATA_TASK_KEY);
		Method method = (Method)dataMap.get(DATA_TRIGGER_KEY);
		Object[] params = (Object[])dataMap.get(DATA_TRIGGER_PARAMS_KEY);
		
		System.out.println(JSON.toJSONString(task));
		try {
			Object invoke = method.invoke(target, params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
