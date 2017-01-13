package cn.followtry.validation.store;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 临时存储，服务重启后存储信息会清零重新计算
 * 
 * @author 
 *		jingzz 
 * @since 
 *		0.0.2
 * @version 
 * 		0.0.2
 */
public class RawStore {
	
	/** 用来存储每个url被请求的次数  */
	private static Map<String, Long> requestFrequencyCache = new ConcurrentHashMap<String,Long>(); 
	
	/** 缓存每个url请求的平均用时，单位为毫秒 */
	private static Map<String, Integer> requestAverageTimeCache = new ConcurrentHashMap<String,Integer>();
	
	/** 每个url请求的最长调用时间 */
	private static Map<String, Integer> requestLongestTimeCache = new ConcurrentHashMap<String,Integer>();
	
	/**每个url请求的第一次调用时间 */
	private static Map<String, Integer> requestFirstDateCache = new ConcurrentHashMap<String,Integer>();
	
	/** 每个url请求最近一次的调用时间  */
	private static Map<String, Integer> requestLastDateCache = new ConcurrentHashMap<String,Integer>();
	
	
}
