package cn.followtry.service.scheduler.util;

import java.util.Calendar;
import java.util.Date;

/**cron表达式工具类.
 *
 * @author jingzz
 * @since 2016年4月26日 下午3:52:55
 */
public class CronUtil {

  /**
   * 将时间转换为要执行的cron点.
   *
   * @param date 被转换的时间
   */
  public static String parseDate2CronExp(Date date) {
    Calendar instance = java.util.Calendar.getInstance();
    instance.setTime(date);
    instance.set(java.util.Calendar.MILLISECOND, 0);

    int second = instance.get(java.util.Calendar.SECOND);
    int minute = instance.get(java.util.Calendar.MINUTE);
    int hour = instance.get(java.util.Calendar.HOUR_OF_DAY);
    int day = instance.get(java.util.Calendar.DAY_OF_MONTH);
    int month = instance.get(Calendar.MONTH) + 1;
    int year = instance.get(java.util.Calendar.YEAR);
    String week = "?";

    StringBuffer expression = new StringBuffer();
    expression.append(second + " ").append(minute + " ").append(hour + " ").append(day + " ")
            .append(month + " ").append(week + " ").append(year + "-" + (year + 1));
    return expression.toString().trim();
  }
}
