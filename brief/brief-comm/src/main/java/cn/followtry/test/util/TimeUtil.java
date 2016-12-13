/**
 * 
 */
package cn.followtry.test.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author jingzz
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
	
	/**
	 * 将yyyy-MM-dd 样式的字符串格式化为Date对象
	 * @param date
	 * @return
	 */
	public static Date formatStr2Date(String date){
		if (date == null || date.split("-").length < 3) {
			return null;
		}
	   long time = formatStrDate2Long(date, "yyyy-MM-dd");
	   return new Date(time);
	}
	
	/**
	 * 将yyyy-MM-dd 样式的字符串格式化为long时间值
	 * @param date 被格式化的时间字符串
	 * @return
	 */
	public static long formatStrDate2Long(String date){
		if (date == null || date.split("-").length < 3) {
			return 0;
		}
		return formatStrDate2Long(date, "yyyy-MM-dd");
	}
	
	/**
	 * 
	 * @param date 时间值
	 * @param pattern 格式化样式
	 * @return
	 */
	public static long formatStrDate2Long(String date,String pattern){
		long time = 0;
		try {
			Date d = new SimpleDateFormat(pattern).parse(date);
			time = d.getTime();
		} catch (ParseException e) {
			time = 0;
			e.printStackTrace();
		}
		return time;
	}

	
	/**
	 * 将字符串格式化为Calendar对象
	 * @param date 时间值
	 * @param pattern 格式化样式
	 * @return
	 */
	public static Calendar formatStr2Calendar(String date,String pattern){
		if (date == null) {
			return null;
		}
		Calendar calendar = null;
		try {
			Date d = new SimpleDateFormat(pattern).parse(date);
			calendar = Calendar.getInstance();
			calendar.clear();
			calendar.setTime(d);
		} catch (ParseException e) {
			calendar = null;
			e.printStackTrace();
		}
		return calendar;
	}
	
	public static Calendar formatStr2Calendar(String date){
		if (date == null) {
			return null;
		}
		return formatStr2Calendar(date, "yyyy-MM-dd");
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
