/**
 * 
 */
package cn.jingzztech.prac.quartz;

import java.text.DateFormat;
import java.util.Date;

/**
 * 测试使用Quartz库
 * @author jingzz
 * @time 2016年2月23日 上午8:56:28
 * @func 
 * @name QuartzTest
 */
public class QuartzTest {
	

	public void work(){
		System.out.println("date:"+DateFormat.getInstance().format(new Date()));
	}

}
