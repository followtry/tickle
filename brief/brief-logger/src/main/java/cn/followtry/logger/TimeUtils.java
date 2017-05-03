/**
 * 
 */
package cn.followtry.logger;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author jingzz
 * @time 2016年7月29日 上午10:59:59
 * @name esn-palmyy-plugin/com.yonyou.esn.palmyy.utils.TimeUtils
 * @since 2016年7月29日 上午10:59:59
 */
public class TimeUtils {
	
	private TimeUtils(){}
	
	
	/**
	 * 将时间格式化为yyyy-MM-dd HH:mm:ss
	 * <p>比如：2016-09-22 13:45:23
	 * @author jingzz
	 * @param timeStamp
	 * @return
	 */
	public static String formatLocalDateTime(long timeStamp){
		String pattern = "yyyy-MM-dd HH:mm:ss";
		return formatDate(new Date(timeStamp), pattern);
	}
	
	public static String formatDate(long timeStamp,String pattern){
		return formatDate(new Date(timeStamp), pattern);
	}
	
	public static String formatDate(Date date,String pattern){
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}
	
	
	/**
	 * 执行同步花费的时间
	 * @author jingzz
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static String syncExecTime(long startTime,long endTime) {
		long inteval = (endTime - startTime) / 1000;// 同步时间，单位为秒
		if (inteval < 60) {
			return inteval +"秒";
		}
		long second = inteval % 60 ;
		if (second == 0) {
			return inteval / 60 +"分";
		}
		return inteval / 60 +"分" + second +"秒";
	}
	
	/**
	 * 获取上次更新时间或者默认时间
	 * @author jingzz
	 * @param time
	 * @param defaultTime
	 * @return
	 */
	public static Date getTimeOrDefault(Date time,long defaultTime){
		return time == null ? new Date(defaultTime) : time;
	}
	
	/**
	 * 获取上次更新时间或者默认时间,默认时间为1970/1/1 00:00:00
	 * @author jingzz
	 * @param time
	 * @return
	 */
	public static Date getTimeOrDefault(Date time){
		long defaultTime = 0; 
		return getTimeOrDefault(time, defaultTime);
	}


	/**
	 * 获取当前的0点0分0秒
	 * @author jingzz
	 * @return
	 */
	public static long getCurDayZeroHour(){
		return getCustomTimeOfDay(0, 0, 0);
	}

	/**
	 * 获取当前的指定时间
	 * @author jingzz
	 * @param hourOfDay
	 * @param minute
	 * @param second
	 * @return
	 */
	public static long getCustomTimeOfDay(int hourOfDay,int minute,int second){
		Calendar instance = Calendar.getInstance();
		instance.set(Calendar.HOUR_OF_DAY, hourOfDay);
		instance.set(Calendar.MINUTE, minute);
		instance.set(Calendar.SECOND, second);
		instance.set(Calendar.MILLISECOND, 0);
		long timeInMillis = instance.getTimeInMillis();
		return timeInMillis;
	}
}
