package cn.followtry.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 该类为了分别测试log4j2的不同功能，因此会指定log4j2的不同功能在不同的配置文件中 Created by followtry on 2017/5/2.
 */
public class LoggerTest {

  private static Logger LOGGER;

  private static String classPath;

  static {
    classPath = Common.class.getClassLoader().getResource("").getPath();
  }

  /** main. */
  public static void main(String[] args) {

    consoleAndKafkaLoggerTest();

//    consoleAndMongoLoggerTest();

    //    consoleAndMailLoggerTest();

    //    consoleAndErrorFileLoggerTest();

//        consoleAndInfoFileLoggerTest();
  }

  public static void consoleAndKafkaLoggerTest() {
    setLogger("log4j2-kafka.xml");
    LOGGER.info("控制台和写kafka的日志信息:test kafka info");
  }


  public static void consoleAndMongoLoggerTest() {
    setLogger("log4j2-mongo.xml");
    LOGGER.info("控制台和写mongo的日志信息:test mongo info");
  }

  public static void consoleAndMailLoggerTest() {
    setLogger("log4j2-mail.xml");
    //    Logger LOG = LoggerFactory.getLogger("asyncMailerLogger");
    //    LOG.error("控制台和发送mail的日志信息:test info info");
    LOGGER.error("控制台和发送mail的日志信息:test mail info");
  }

  public static void consoleAndInfoFileLoggerTest() {
    setLogger("log4j2-console-file.xml");
    LOGGER.info("控制台和普通info级别的日志信息:test info info");
  }

  public static void consoleAndErrorFileLoggerTest() {
    setLogger("log4j2-error-specific.xml");
    LOGGER.error("指定类或包的error级别的日志信息:{}","test error info");
  }

  private static void setLogger(String loggerConfigFileName) {
    System.setProperty("log4j.configurationFile",classPath + loggerConfigFileName);
    LOGGER = LoggerFactory.getLogger(LoggerTest.class);
  }


}
