package cn.followtry.incubate.java.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by followtry on 2017/4/19.
 */
public class LoggerUtil {

	private LoggerUtil(){}

	private static final Logger LOGGERLEVEL=LoggerFactory.getLogger("levelLogger");
	private static final Logger LOGGERMAIL=LoggerFactory.getLogger("MailLogger");

	/**
	 * 打印error级别的日志信息
	 * @param sendMail 是否发送邮件
	 * @param format 格式化字符串
	 * @param args 参数
	 */
	public static void error(Boolean sendMail, String format, Object... args){
		if (sendMail) {
			LOGGERMAIL.error(format,args);
		}
		LOGGERLEVEL.error(format,args);

	}
}
