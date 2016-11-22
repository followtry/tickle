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
		
		Logger logger = LoggerFactory.getLogger(serviceTypeName);
		logger.info(String.format("Service begin... --[ACTION=%s]", methodName));
		
		long elapsedTs = 0;
		try {
			long startTs = System.currentTimeMillis();
			
			serviceChain.doService(joinPoint);
			
			Object retn = joinPoint.proceed();
			
			elapsedTs  = System.currentTimeMillis() - startTs;
			return retn;
		}catch(RuntimeException e){
			logger.error(String.format("Service occurred runtimeException! --[ACTION=%s] --%s", methodName, e.getMessage()), e);
			throw new RuntimeException(e);
		} catch (Throwable e) {
			logger.error(String.format("Service occurred throwable! --[ACTION=%s] --%s", methodName, e.getMessage()), e);
			throw new RuntimeException(e);
		}finally {
			logger.info(String.format("Service completed. --[ACTION=%s][TIME=%s ms]", methodName, elapsedTs));
		}
	}
	
	protected Object controllerLogAround(ProceedingJoinPoint joinPoint){
		Signature signature = joinPoint.getSignature();
		String methodName = signature.toLongString();
		String serviceTypeName = signature.getDeclaringTypeName();
		Logger logger = LoggerFactory.getLogger(serviceTypeName);
		logger.info(String.format("Controller begin... --[ACTION=%s]", methodName));
		
		long elapsedTs = 0;
		try {
			long startTs = System.currentTimeMillis();
			
			serviceChain.doService(joinPoint);
			
			Object retn = joinPoint.proceed();
			
			elapsedTs  = System.currentTimeMillis() - startTs;
			return retn;
		}catch(RuntimeException e){
			logger.error(String.format("Controller occurred runtimeException! --[ACTION=%s] --%s", methodName, e.getMessage()), e);
			throw new RuntimeException(e);
		} catch (Throwable e) {
			logger.error(String.format("Controller occurred throwable! --[ACTION=%s] --%s", methodName, e.getMessage()), e);
			throw new RuntimeException(e);
		}finally {
			logger.info(String.format("Controller completed. --[ACTION=%s][TIME=%s ms]", methodName, elapsedTs));
		}
	}
	
}
