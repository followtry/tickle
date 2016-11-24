/**
 * 
 */
package cn.followtry.quartz;

import java.util.UUID;

/**
 * @author jingzz
 * @time 2016年11月24日 下午1:15:33
 * @name brief-quartz/cn.followtry.quartz.UUID
 * @since 2016年11月24日 下午1:15:33
 */
public class UUIDUtil {
	
	private UUIDUtil(){}
	
	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}
}
