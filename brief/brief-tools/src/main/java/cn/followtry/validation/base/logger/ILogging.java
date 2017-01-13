/**
 * 
 */
package cn.followtry.validation.base.logger;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @author followtry
 * 			创建人
 * @since 2016年11月23日 下午1:33:09 
 * 			创建时间
 */
public interface ILogging {
	
	/**
	 * 环绕日志<br>
	 * 根据不同的注解类型调用不同的日志显示
	 * @param joinPoint 切入点
	 * @return 执行体
	 */
	Object loggingAround(ProceedingJoinPoint joinPoint);
}
