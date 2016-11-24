/**
 * 
 */
package cn.followtry.quartz.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jingzz
 * @time 2016年11月24日 上午10:16:10
 * @name brief-quartz/cn.followtry.quartz.task.MyTask
 * @since 2016年11月24日 上午10:16:10
 */
public class MyTask {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MyTask.class);
	
	public String sayHello(){
		LOGGER.info("MyTask.sayHello()");
		System.out.println("MyTask.sayHello()");
		return null;
	}
}
