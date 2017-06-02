package cn.followtry.zk.utils;

import java.util.Properties;
import org.apache.curator.RetryPolicy;
import org.apache.curator.retry.RetryForever;
import org.apache.curator.retry.RetryNTimes;
import org.apache.curator.retry.RetryOneTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by followtry on 2017/5/31.
 */
public class ZkProp {
  
  private static final Logger LOGGER = LoggerFactory.getLogger(ZkProp.class);
  
  private static Properties prop = ConfigHelper.getProp();
  
  //session默认过期值，30分钟
  private static final int DEFAULT_SESSION_TIMEOUT_MS = 30 * 60 * 1000;
  
  //连接超时时间，默认为30秒
  private static final int DEFAULT_CONNECTION_TIMEOUT_MS = 30 * 1000;
  
  //重试时间间隔，默认为10秒
  private static final int DEFAULT_RETRY_INTEVAL_MS = 10 * 1000;
  //重试次数，默认为3
  private static final int DEFAULT_RETRY_TIMES = 3;
  
  //默认zookeeper节点地址
  private static final String DEFAULT_ZK_NODE = "127.0.0.1:2181";
  
  private static final String DEFAULT_RETRY_POLICY = "oneTime";
  
  private static final String DEFAULT_CAN_BE_READONLY = "false";
  
  public static String connectionString() {
    return prop.getProperty("connectionString",DEFAULT_ZK_NODE);
  }
  
  public static int sessionTimeoutMs() {
    return Integer.valueOf(prop.getProperty("sessionTimeoutMs",String.valueOf(DEFAULT_SESSION_TIMEOUT_MS)));
  }
  
  public static int connectionTimeoutMs() {
    return Integer.valueOf(prop.getProperty("connectionTimeoutMs",String.valueOf(DEFAULT_CONNECTION_TIMEOUT_MS)));
  }
  
  public static RetryPolicy retryPolicy() {
    String retryPolicy = prop.getProperty("retryPolicy",DEFAULT_RETRY_POLICY);
    RetryPolicy rp;
    switch (retryPolicy) {
      case "oneTime":
        rp = new RetryOneTime(retryInteval());
        break;
      case "NtTime":
        rp = new RetryNTimes(retryTimes(),retryInteval());
        break;
      case "forever":
        rp = new RetryForever(retryInteval());
        break;
      default:
        LOGGER.warn("retryPolicy not set,now setting default retryPolicy is retryOneTime,retryInteval is {}ms",retryInteval());
        rp = new RetryOneTime(retryInteval());
    }
    return rp;
  }
  
  public static Boolean canBeReadOnly() {
    String canBeReadOnly1 = prop.getProperty("canBeReadOnly",DEFAULT_CAN_BE_READONLY);
    Boolean canBeReadOnly;
    switch (canBeReadOnly1) {
      case "true":
        canBeReadOnly = Boolean.parseBoolean(canBeReadOnly1);
        break;
      case "false":
        canBeReadOnly = Boolean.parseBoolean(canBeReadOnly1);
        break;
      default:
        canBeReadOnly = Boolean.parseBoolean("false");
    }
    return canBeReadOnly;
  }
  
  private static int retryInteval() {
    return Integer.valueOf(prop.getProperty("retryInteval",String.valueOf(DEFAULT_RETRY_INTEVAL_MS)));
  }
  
  private static int retryTimes() {
    return Integer.valueOf(prop.getProperty("retryTimes",String.valueOf(DEFAULT_RETRY_TIMES)));
  }
  
}
