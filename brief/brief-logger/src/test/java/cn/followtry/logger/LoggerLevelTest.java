package cn.followtry.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 ** 测试将日志写入到指定的不同的文件中
 * Created by followtry on 2017/4/18.
 */
public class LoggerLevelTest {
//	private static final Logger LOGGER_LEVEL=LoggerFactory.getLogger("errorEsnLogger");
//	private static final Logger LOGGER_DB=LoggerFactory.getLogger("AsyncDBLogger");
	private static final Logger LOGGER_MAIL= LoggerFactory.getLogger("AsyncMailerLogger");
//	private static final Logger LOGGER=LoggerFactory.getLogger(LoggerLevelTest.class);
//	private static final Logger LOGGER_Nosql=LoggerFactory.getLogger("noSqlDbLogger");



	public static void main(String[] args) {
		try {
			String str=null;
			System.out.println(str.length());
		} catch(Exception e) {

			LoggerBody loggerBody = LoggerBody.getObj().setMsg("指定日志发送位置222222").setDetailInfo(e.toString());

//			LOGGER_Nosql.error(loggerBody.toString());
//			LOGGER_LEVEL.error(loggerBody.toString());
//			LOGGER_DB.error(loggerBody.toString());
			LOGGER_MAIL.error(loggerBody.toString());
//			LOGGER_MAIL.info(loggerBody.toString());
		}
	}
}
