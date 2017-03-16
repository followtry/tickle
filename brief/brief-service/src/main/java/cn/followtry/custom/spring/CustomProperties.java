/**
 * 
 */
package cn.followtry.custom.spring;

import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

/**
 * 获取自定义的系统属性
 * @author jingzz
 * @time 2016年10月12日 上午9:36:08
 * @name brief-comm/cn.followtry.test.sysprop.CustomProperties
 * @since 2016年10月12日 上午9:36:08
 */
public class CustomProperties {
	
	private static Properties props;
	
	private static final Logger LOG = LoggerFactory.getLogger(CustomProperties.class);
	
	private CustomProperties() {
		
	}
	
	static{
		try {
            Resource resource = new ClassPathResource("/application.properties");//
            props = PropertiesLoaderUtils.loadProperties(resource);
        } catch (IOException e) {
        	LOG.error("出现异常",e);
        }
	}

	public static String getProperty(String key){
        return props == null ? null :  props.getProperty(key);
    }
	
}
