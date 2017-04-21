package cn.followtry.validation.base.logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;

import cn.followtry.validation.base.AspectServiceChain;
import cn.followtry.validation.base.DefaultAspectServiceChain;
import cn.followtry.validation.base.constant.AspectOrders;
import cn.followtry.validation.base.stereotype.XComponent;
import cn.followtry.validation.base.stereotype.XController;
import cn.followtry.validation.base.stereotype.XRepository;
import cn.followtry.validation.base.stereotype.XService;
import cn.followtry.validation.utils.AnnoUtils;

public abstract class LoggingAspectBase implements ILogging, Ordered {

	private static AspectServiceChain serviceChain = new DefaultAspectServiceChain();

	private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspectBase.class);

	@Override
	public int getOrder() {
		return AspectOrders.LOGGING;
	}

	protected Object serviceLogAround(ProceedingJoinPoint joinPoint) throws Throwable {
		Object target = joinPoint.getTarget();
		XService annotation = AnnoUtils.getAnno(target, XService.class);
		if (annotation == null) {
		} else {
			if (annotation.allMethods()) {

				return loggingAround(joinPoint);
			} else {
				// 获取当前的方法名称
				String shortMethodName = ((MethodSignature) joinPoint.getSignature()).getMethod().getName();
				String[] monitorMethods = annotation.monitorMethods();

				// 没有需要监控的方法，直接跳过
				if (monitorMethods == null || monitorMethods.length == 0) {
					return invoke(joinPoint);
				}
				for (String method : monitorMethods) {
					if (shortMethodName.equals(method)) {
						return loggingAround(joinPoint);
					}
				}
			}
		}
		// 不存在于需要监控的方法中，直接跳过监控继续执行
		return invoke(joinPoint);
	}

	protected Object repositoryLogAround(ProceedingJoinPoint joinPoint) throws Throwable {
		Object target = joinPoint.getTarget();
		XRepository annotation = AnnoUtils.getAnno(target, XRepository.class);
		if (annotation == null) {
		} else {
			if (annotation.allMethods()) {

				return loggingAround(joinPoint);
			} else {
				// 获取当前的方法名称
				String shortMethodName = ((MethodSignature) joinPoint.getSignature()).getMethod().getName();
				String[] monitorMethods = annotation.monitorMethods();

				// 没有需要监控的方法，直接跳过
				if (monitorMethods == null || monitorMethods.length == 0) {
					return invoke(joinPoint);
				}
				for (String method : monitorMethods) {
					if (shortMethodName.equals(method)) {
						return loggingAround(joinPoint);
					}
				}
			}
		}
		// 不存在于需要监控的方法中，直接跳过监控继续执行
		return invoke(joinPoint);
	}

	protected Object componentLogAround(ProceedingJoinPoint joinPoint) throws Throwable {
		Object target = joinPoint.getTarget();
		XComponent annotation = AnnoUtils.getAnno(target, XComponent.class);
		if (annotation == null) {
		} else {
			if (annotation.allMethods()) {

				return loggingAround(joinPoint);
			} else {
				// 获取当前的方法名称
				String shortMethodName = ((MethodSignature) joinPoint.getSignature()).getMethod().getName();
				String[] monitorMethods = annotation.monitorMethods();

				// 没有需要监控的方法，直接跳过
				if (monitorMethods == null || monitorMethods.length == 0) {
					return invoke(joinPoint);
				}
				for (String method : monitorMethods) {
					if (shortMethodName.equals(method)) {
						return loggingAround(joinPoint);
					}
				}
			}
		}
		// 不存在于需要监控的方法中，直接跳过监控继续执行
		return invoke(joinPoint);
	}

	protected Object controllerLogAround(ProceedingJoinPoint joinPoint) throws Throwable {
		Object target = joinPoint.getTarget();
		XController annotation = AnnoUtils.getAnno(target, XController.class);
		if (annotation == null) {
		} else {
			if (annotation.allMethods()) {

				return loggingAround(joinPoint);
			} else {
				// 获取当前的方法名称
				String shortMethodName = ((MethodSignature) joinPoint.getSignature()).getMethod().getName();
				String[] monitorMethods = annotation.monitorMethods();

				// 没有需要监控的方法，直接跳过
				if (monitorMethods == null || monitorMethods.length == 0) {
					return invoke(joinPoint);
				}
				for (String method : monitorMethods) {
					if (shortMethodName.equals(method)) {
						return loggingAround(joinPoint);
					}
				}
			}
		}
		// 不存在于需要监控的方法中，直接跳过监控继续执行
		return invoke(joinPoint);
	}

	/**
	 * 对调用的方法进行日志环绕，打印监控信息
	 */
	@Override
	public Object loggingAround(ProceedingJoinPoint joinPoint) {
		Signature signature = joinPoint.getSignature();
		String methodName = signature.toLongString();

		LOGGER.debug("begin... --[ACTION={}]", methodName);
		long elapsedTs = -1;
		long startTs = 0;
		Object retn = null;
		try {
			serviceChain.doCheck(joinPoint);
			startTs = System.currentTimeMillis();
			retn = invoke(joinPoint);
			return retn;
		} catch (RuntimeException e) {
			LOGGER.error("occurred runtimeException! --[ACTION={}] --{}", methodName, e.getMessage(), e);
			throw e;
		} catch (Throwable e) {
			LOGGER.error("occurred throwable! --[ACTION={}] --{}", e.getMessage(), e);
			throw new RuntimeException(e);
		} finally {
			elapsedTs = System.currentTimeMillis() - startTs;
			String params = getArgsTypeAndValues(joinPoint);
			LOGGER.debug("args list are [{}]", params);
			LOGGER.debug("target method is [{}]", methodName);
			LOGGER.debug("result of [{}] is [{}]", methodName, retn);
			LOGGER.info("completed. --[COMPLETED TIME={} ms][ACTION={}]", elapsedTs, methodName);
		}
	}

	/**
	 * 实际调用切面能力的地方
	 * @author jingzz
	 * @param joinPoint
	 * @return
	 * @throws Throwable
	 */
	private Object invoke(ProceedingJoinPoint joinPoint) throws Throwable {
		return joinPoint.proceed();
	}

	/**
	 * @param joinPoint
	 *            切入点
	 * @return 返回组装的参数列表格式为type1:value,type2:value
	 */
	private String getArgsTypeAndValues(ProceedingJoinPoint joinPoint) {
		Object[] args = joinPoint.getArgs();
		StringBuilder params = new StringBuilder();
		int arglength = args.length;
		for (int i = 0; i < arglength; i++) {
			if (args[i] == null) {
				params.append("null:null");
				continue;
			}
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
