package com.yonyou.tools.validation.base.logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import com.yonyou.tools.validation.base.aop.AspectJointPoints;

/**
 * 对 Service、Data 层的通用日志处理；
 * 
 * @author haiq
 * @author jingzz
 * 
 *
 */
@Aspect
public class LoggingAspect extends LoggingAspectBase {
	
	/**
	 * 记录业务服务接口的调用日志；
	 * 
	 * @param joinPoint
	 */
	@Around(AspectJointPoints.BUSINESS_SERVICE_ACTIONS)
	private Object onServiceInvoked(ProceedingJoinPoint joinPoint) {
		return serviceLogAround(joinPoint);
	}
	
	/**
	 * 记录controller接口的调用日志
	 * @author jingzz
	 * @param joinPoint
	 * @return
	 */
	@Around(AspectJointPoints.BUSINESS_CONTROLLER_ACTIONS)
	private Object onControllerInvoked(ProceedingJoinPoint joinPoint) {
		return controllerLogAround(joinPoint);
	}

	/**
	 * 记录数据访问服务接口的调用日志；
	 * 
	 * @param joinPoint
	 */
	@Around(AspectJointPoints.BUSINESS_DATA_ACCESS_ACTIONS)
	private Object onRepositoryInvoked(ProceedingJoinPoint joinPoint) {
		return serviceLogAround(joinPoint);
	}

	/**
	 * 记录外部服务接口的调用日志；
	 * 
	 * @param joinPoint
	 */
	@Around(AspectJointPoints.EXTERNAL_SERVICE_ACTIONS)
	private Object onExternalServiceInvoked(ProceedingJoinPoint joinPoint) {
		return serviceLogAround(joinPoint);
	}

}
