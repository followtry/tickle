/**
 * 
 */
package cn.followtry.service.scheduler.util;

import java.util.Calendar;
import java.util.Date;

/**
 * @author jingzz
 * @time 2016年4月26日 下午3:52:55
 * @name service-scheduler-impl/com.yonyou.worktime.service.scheduler.util.CronUtil
 * @since 2016年4月26日 下午3:52:55
 */
public class CronUtil {
	
	/**
	 * 将时间转换为要执行的cron点
	 * @author jingzz
	 * @param date 被转换的时间
	 * @return
	 */
	public static String parseDate2CronExp(Date date) {
		Calendar instance = Calendar.getInstance();
		instance.setTime(date);
		instance.set(Calendar.MILLISECOND, 0);
		
		int second = instance.get(Calendar.SECOND);
		int minute = instance.get(Calendar.MINUTE);
		int hour = instance.get(Calendar.HOUR_OF_DAY);
		int day = instance.get(Calendar.DAY_OF_MONTH);
		int month = instance.get(Calendar.MONTH)+1;
		int year = instance.get(Calendar.YEAR);
		String week = "?";
		
		StringBuffer expression = new StringBuffer();
		expression.append(second+" ")
				.append(minute+" ")
				.append(hour+" ")
				.append(day+" ")
				.append(month+" ")
				.append(week+" ")
				.append(year+"-"+(year+1));
		return expression.toString().trim();
	}
}
