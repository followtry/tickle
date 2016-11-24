package cn.followtry.validation.base.aop;

/**
 * 切面连接点；
 * 
 * @author haiq
 * @author followtry
 *
 */
public interface AspectJointPoints {

	/** 业务服务接口的全部操作方法 **/
	public static final String SERVICE_ACTIONS = "@within(cn.followtry.validation.base.stereotype.XService)";
	
	/** 业务控制器接口的全部操作方法 **/
	public static final String CONTROLLER_ACTIONS = "@within(cn.followtry.validation.base.stereotype.XController)";
	
	/** 数据访问服务接口的全部操作方法；**/
	public static final String DATA_ACCESS_ACTIONS = "@within(cn.followtry.validation.base.stereotype.XRepository)";
	
	/** 组件的全部操作方法；**/
	public static final String COMPONENT_ACTIONS = "@within(cn.followtry.validation.base.stereotype.XComponent)";
}
