/**
 * 
 */
package cn.jingzz.brief.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author jingzz
 * @time 2016年9月8日 下午4:46:03
 * @name yycollege/com.yonyou.esn.yycollege.utils.TimeUtil
 * @since 2016年9月8日 下午4:46:03
 */
public class TimeUtil {
	
	private TimeUtil() {
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
	
	public static long getCustomTime(int year,int month,int dayOfMonth,int hourOfDay,int minute,int second){
		Calendar instance = Calendar.getInstance();
		month = month -1;
		instance.set(year, month, dayOfMonth);
		instance.set(Calendar.HOUR_OF_DAY, hourOfDay);
		instance.set(Calendar.MINUTE, minute);
		instance.set(Calendar.SECOND, second);
		instance.set(Calendar.MILLISECOND, 0);
		long timeInMillis = instance.getTimeInMillis();
		return timeInMillis;
	}
	
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
	
	public static void main(String[] args) {
		System.out.println(formatLocalDateTime(System.currentTimeMillis()));
	}
	
	/**
	 * 获取当前的0点0分0秒
	 * @author jingzz
	 * @return
	 */
	public static long getCurDayZeroHour(){
		return getCustomTimeOfDay(0, 0, 0);
	}
	
	public static long getTomorrowZeroHour(){
		return getCustomTimeOfDay(24, 0, 0);
	}
}
