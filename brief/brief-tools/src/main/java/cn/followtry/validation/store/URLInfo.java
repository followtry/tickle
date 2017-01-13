package cn.followtry.validation.store;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 存储与url请求相关的基本信息
 * 
 * @author jingzz
 * @since
 * 
 * @version
 * 
 */
public class URLInfo {

	// 请求的次数
	private AtomicLong requestTimes;

	// 请求的总用时，单位为毫秒
	private AtomicLong requestTotalTimes;

	/** 每个url请求的最长调用时间 */
	private static Integer requestLongestTime;

	/** 每个url请求的第一次调用时间 */
	private static Long requestFirstDate;

	/** 每个url请求最近一次的调用时间 */
	private static Long requestLastDate;

}
