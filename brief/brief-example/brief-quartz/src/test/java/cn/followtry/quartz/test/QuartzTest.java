/**
 * 
 */
package cn.followtry.quartz.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.followtry.quartz.TaskService;
import cn.followtry.quartz.UUIDUtil;
import cn.followtry.quartz.ScheduleTask;
import cn.followtry.quartz.task.MyTask;

/**
 * @author followtry
 * @since 2016年11月24日 上午9:40:43
 */
@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:/applicationContext.xml"})
public class QuartzTest extends AbstractJUnit4SpringContextTests{
	
	@Autowired
	private TaskService taskService;
	
	@Test 
	public void taskServicetest() throws Exception{
		ScheduleTask task = new ScheduleTask();
		String taskId = String.valueOf(UUIDUtil.getUUID());
		task.setId(taskId);
		task.setGroup(MyTask.class.getCanonicalName());
		task.setTrigger("sayHello");
		task.setCron("0/2 * * * * ? ");
		taskService.addTask(task);
		while(true){}
	}
	
}
