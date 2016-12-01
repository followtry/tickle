package cn.followtry.validation.base.logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;

import cn.followtry.validation.base.AnnotationType;
import cn.followtry.validation.base.AspectServiceChain;
import cn.followtry.validation.base.DefaultAspectServiceChain;
import cn.followtry.validation.base.aop.AspectOrders;

public class LoggingAspectBase implements ILogging, Ordered {

	private static AspectServiceChain serviceChain = new DefaultAspectServiceChain();

	@Override
	public int getOrder() {
		return AspectOrders.LOGGING;
	}

	protected Object serviceLogAround(ProceedingJoinPoint joinPoint) {
		return loggingAround(AnnotationType.SERVICE, joinPoint);
	}

	protected Object repositoryLogAround(ProceedingJoinPoint joinPoint) {
		return loggingAround(AnnotationType.REPOSITORY, joinPoint);
	}

	protected Object componentLogAround(ProceedingJoinPoint joinPoint) {
		return loggingAround(AnnotationType.COMPONENT, joinPoint);
	}

	protected Object controllerLogAround(ProceedingJoinPoint joinPoint) {
		return loggingAround(AnnotationType.CONTROLLER, joinPoint);
	}

	@Override
	public Object loggingAround(AnnotationType annoType, ProceedingJoinPoint joinPoint) {
		String annoTypeName = annoType.name().toLowerCase();
		Signature signature = joinPoint.getSignature();
		String methodName = signature.toLongString();
		String serviceTypeName = signature.getDeclaringTypeName();
		Logger logger = LoggerFactory.getLogger(serviceTypeName);
		logger.debug("{} begin... --[ACTION={}]", annoTypeName, methodName);
		long elapsedTs = -1;
		long startTs = 0;
		Object retn = null;
		try {

			serviceChain.doCheck(joinPoint);
			startTs = System.currentTimeMillis();
			retn = joinPoint.proceed();
			return retn;
		} catch (RuntimeException e) {
			logger.error("{} occurred runtimeException! --[ACTION={}] --{}", annoTypeName, methodName,
					e.getMessage(), e);
			throw e;
		} catch (Throwable e) {
			logger.error("{} occurred throwable! --[ACTION={}] --{}", annoTypeName, e.getMessage(), e);
			throw new RuntimeException(e);
		} finally {
			elapsedTs = System.currentTimeMillis() - startTs;
			String params = getArgsTypeAndValues(joinPoint);
			logger.debug("args list are [{}]", params);
			logger.debug("target method is [{}]", methodName);
			logger.debug("result of [{}] is [{}]", methodName, retn);
			logger.info("{} completed. --[COMPLETED TIME={} ms][ACTION={}]", annoTypeName, elapsedTs,
					methodName);
		}
	}

	/**
	 * @param joinPoint 切入点
	 * @return 返回组装的参数列表格式为type1:value,type2:value
	 */
	private String getArgsTypeAndValues(ProceedingJoinPoint joinPoint) {
		Object[] args = joinPoint.getArgs();
		StringBuilder params = new StringBuilder();
		int arglength = args.length;
		for (int i = 0; i < arglength; i++) {
			params.append(args[i].getClass().getSimpleName());
			params.append(":");
			params.append(args[i]);
			if (i < arglength - 1) {
				params.append(",");
			}
		}
		return params.toString();
	}
}
