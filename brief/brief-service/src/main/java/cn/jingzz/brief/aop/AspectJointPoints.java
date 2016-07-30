package cn.jingzz.brief.aop;

/**
 * 切面连接点；
 * 
 * @author haiq
 *
 */
public class AspectJointPoints {

	/**
	 * 业务服务接口的全部操作方法；
	 */
	public static final String BUSINESS_SERVICE_ACTIONS = "@within(org.springframework.stereotype.Service)";

	/**
	 * 数据访问服务接口的全部操作方法；
	 */
	public static final String DATA_ACCESS_SERVICE_ACTIONS = "@within(org.springframework.stereotype.Repository)";

	/**
	 * 外部服务接口的全部操作方法；
	 */
	public static final String EXTERNAL_SERVICE_ACTIONS = "@within(com.yonyou.worktime.base.stereotype.service.ExternalService)";

}
