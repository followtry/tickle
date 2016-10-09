/**
 * 
 */
package cn.followtry.test.sysprop;

import java.util.Properties;
import java.util.Set;

/**
 * @author jingzz
 * @time 2016年8月5日 下午3:56:47
 * @name esn-palmyy-plugin/com.yonyou.esn.palmyy.service.test
 * @since 2016年8月5日 下午3:56:47
 */
public class SystemProperties {
	
	public static void main(String[] args) {
		Properties properties = System.getProperties();
		Set<Object> keys = properties.keySet();
		for (Object key : keys) {
			System.out.println("key="+key+",value="+ properties.getProperty((String)key));
		}
		System.out.println(System.getProperty("catalina.home"));
		System.out.println("结束");
	}
}
