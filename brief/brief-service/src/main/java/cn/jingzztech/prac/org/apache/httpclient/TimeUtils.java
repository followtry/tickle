package cn.jingzztech.prac.org.apache.httpclient;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 有关时间操作的工具方法
 * @author jingzz
 * @time 2016年1月27日 上午8:57:49
 * @func 
 * @name TimeUtils
 */
public class TimeUtils {
	
	private static final Logger LOG = LoggerFactory.getLogger(TimeUtils.class);

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
			LOG.error("格式化时间出错",e.toString());
		}
		return time;
	}

	/**
	 * 获取日期，只包含年月日
	 * @param date 指定时间
	 * @return 下一天的零点零分零秒
	 */
	public static Date getNextDateOnlyYMD(Date date) {
		if (date == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}
	
	/**
	 * 将date格式化为指定格式yyyy-MM-dd
	 * @param date
	 * @return
	 */
	public static String formatDate2Str(Date date){
		if (date == null) {
			return null;
		}
		return formatDate2Str(date, "yyyy-MM-dd");
	}
	
	/**
	 * 将date格式化为指定的格式
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String formatDate2Str(Date date,String pattern){
		if (date == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date); 
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
			LOG.error("格式化时间出错",e.toString());
		}
		return calendar;
	}
	
	public static Calendar formatStr2Calendar(String date){
		if (date == null) {
			return null;
		}
		return formatStr2Calendar(date, "yyyy-MM-dd");
	}

}
