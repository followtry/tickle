package cn.followtry.validation.base.logger;

import javax.annotation.Resource;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;

import cn.followtry.validation.base.AspectServiceChain;
import cn.followtry.validation.base.aop.AspectOrders;

public class LoggingAspectBase implements Ordered{
	
	@Resource
	private AspectServiceChain serviceChain;
	
	@Override
	public int getOrder() {
		return AspectOrders.LOGGING;
	}
	
	protected Object serviceLogAround(ProceedingJoinPoint joinPoint){
		Signature signature = joinPoint.getSignature();
		String methodName = signature.toLongString();
		String serviceTypeName = signature.getDeclaringTypeName();
//		String serviceMethodName = signature.getName();
		
		Logger logger = LoggerFactory.getLogger(serviceTypeName);
		logger.info(String.format("Service begin... --[ACTION=%s]", methodName));
		
		try {
			long startTs = System.currentTimeMillis();
			
			serviceChain.doService(joinPoint);
			
			Object retn = joinPoint.proceed();
			
			long elapsedTs = System.currentTimeMillis() - startTs;
			logger.info(String.format("Service completed. --[ACTION=%s][TIME=%s ms]", methodName, elapsedTs));
			return retn;
		} catch (Throwable e) {
			logger.error(String.format("Service occurred error! --[ACTION=%s] --%s", methodName, e.getMessage()), e);
			
			throw new RuntimeException(e);
		}
	}
	
	protected Object controllerLogAround(ProceedingJoinPoint joinPoint){
		Signature signature = joinPoint.getSignature();
		String methodName = signature.toLongString();
		String serviceTypeName = signature.getDeclaringTypeName();
//		String serviceMethodName = signature.getName();
//		String targetName = joinPoint.getTarget().getClass().getName();
//		Object[] args = joinPoint.getArgs();
//		String kind = joinPoint.getKind();
//		Annotation annotation = joinPoint.getSignature().getDeclaringType().getAnnotation(ExtendController.class);
//		
//		Class withinType = joinPoint.getStaticPart().getSourceLocation().getWithinType();
		Logger logger = LoggerFactory.getLogger(serviceTypeName);
		logger.info(String.format("Controller begin... --[ACTION=%s]", methodName));
		
		try {
			long startTs = System.currentTimeMillis();
			
			serviceChain.doService(joinPoint);
			
			Object retn = joinPoint.proceed();
			
			long elapsedTs = System.currentTimeMillis() - startTs;
			logger.info(String.format("Controller completed. --[ACTION=%s][TIME=%s ms]", methodName, elapsedTs));
			return retn;
		} catch (Throwable e) {
			logger.error(String.format("Controller occurred error! --[ACTION=%s] --%s", methodName, e.getMessage()), e);
			throw new RuntimeException(e);
		}
	}
	
}
