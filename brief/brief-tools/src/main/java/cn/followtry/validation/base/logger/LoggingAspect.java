package cn.followtry.validation.base.logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import cn.followtry.validation.base.constant.AspectJointPoints;

/**
 * 对 Service、Data 层的通用日志处理；
 *
 * @author haiq
 * @author followtry
 */
@Aspect
public class LoggingAspect extends AbstractLoggingAspect {

  /**
   * 记录业务服务接口的调用日志；
   */
  @Around(AspectJointPoints.SERVICE_ACTIONS)
  private Object onServiceInvoked(ProceedingJoinPoint joinPoint) throws Throwable {
    return serviceLogAround(joinPoint);
  }

  /**
   * 记录控制器接口的调用日志
   */
  @Around(AspectJointPoints.CONTROLLER_ACTIONS)
  private Object onControllerInvoked(ProceedingJoinPoint joinPoint) throws Throwable {
    return controllerLogAround(joinPoint);
  }

  /**
   * 记录数据访问服务接口的调用日志；
   *
   * @param joinPoint 切入点
   */
  @Around(AspectJointPoints.DATA_ACCESS_ACTIONS)
  private Object onRepositoryInvoked(ProceedingJoinPoint joinPoint) throws Throwable {
    return repositoryLogAround(joinPoint);
  }

  /**
   * 组件访问的调用日志
   *
   * @param joinPoint 切入点
   */
  @Around(AspectJointPoints.COMPONENT_ACTIONS)
  private Object onComponentInvoked(ProceedingJoinPoint joinPoint) throws Throwable {
    return componentLogAround(joinPoint);
  }
}
