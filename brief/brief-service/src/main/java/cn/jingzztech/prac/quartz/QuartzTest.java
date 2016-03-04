/**
 * 
 */
package cn.jingzztech.prac.quartz;

import java.text.DateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * 测试使用Quartz库
 * @author jingzz
 * @time 2016年2月23日 上午8:56:28
 * @func 
 * @name QuartzTest
 */
@EnableScheduling
public class QuartzTest {
	
	
	public void work(){
		System.out.println("work-->date:"+DateFormat.getInstance().format(new Date()));
	}
	
	
	@Scheduled(cron="*/1 * * * * ?")
	public void springTaskSchedule(){
		System.out.println("springTaskSchedule--->date:"+DateFormat.getInstance().format(new Date()));
	}
	
	public void springTaskSchedule2(){
		System.out.println("springTaskSchedule2--->date:"+DateFormat.getInstance().format(new Date()));
	}

}
