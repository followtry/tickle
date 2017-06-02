package cn.followtry.zk.utils;

import com.google.common.io.Resources;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 从配置文件zk.properties中载入zookeeper的配置属性
 * Created by followtry on 2017/5/31.
 */
public class ConfigHelper {
  
  private static final Logger LOGGER = LoggerFactory.getLogger(ConfigHelper.class);
  
  private static Properties properties = new Properties();
  
  static {
      URL resource = Resources.getResource("zk.properties");
    try {
      properties.load(resource.openStream());
    } catch (IOException e) {
      LOGGER.error("载入配置异常",e);
    }
  }
  
  private ConfigHelper(){
    // 不允许外部实例化
  }
  
  public static Properties getProp() {
    return properties;
  }
}
