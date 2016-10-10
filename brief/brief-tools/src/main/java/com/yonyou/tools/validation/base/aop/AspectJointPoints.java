package com.yonyou.tools.validation.base.aop;

/**
 * 切面连接点；
 * 
 * @author haiq
 * @author jingzz
 *
 */
public interface AspectJointPoints {

	/**
	 * 业务服务接口的全部操作方法；
	 */
	public static final String BUSINESS_SERVICE_ACTIONS = "@within(com.yonyou.tools.validation.base.stereotype.XService)";
	
	/**
	 * 业务控制器接口的全部操作方法
	 */
	public static final String BUSINESS_CONTROLLER_ACTIONS = "@within(com.yonyou.tools.validation.base.stereotype.XController)";

	/**
	 * 数据访问服务接口的全部操作方法；
	 */
	public static final String BUSINESS_DATA_ACCESS_ACTIONS = "@within(com.yonyou.tools.validation.base.stereotype.XRepository)";

	/**
	 * 外部服务接口的全部操作方法；
	 */
	public static final String EXTERNAL_SERVICE_ACTIONS = "@within(com.yonyou.tools.validation.base.stereotype.ExternalService)";

}
