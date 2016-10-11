package com.yonyou.tools.validation.base.validation;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.CatchClauseSignature;
import org.aspectj.lang.reflect.FieldSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;

import com.yonyou.tools.validation.base.aop.AspectOrders;
import com.yonyou.tools.validation.base.stereotype.validation.ValidationException;

/**
 * 对字段约束进行校验；
 * 
 * @author haiq
 *
 */
//@Aspect
public class ValidationAspect implements Ordered {

	private final Object mutex = new Object();

	private Map<String, MethodSignatureValidate> validators = new ConcurrentHashMap<String, MethodSignatureValidate>();

	@Override
	public int getOrder() {
		return AspectOrders.VALIDATION;
	}

	public void check(ProceedingJoinPoint joinpoint) throws Throwable {
		// 校验数据，如果未通过，将抛出 ValidationException 异常；
		validate(joinpoint);
	}

	private void validate(ProceedingJoinPoint joinpoint) throws ValidationException {
		Signature signature = joinpoint.getSignature();
		//方法签名
		if (signature instanceof MethodSignature) {//方法校验
			MethodSignature methodSignature = (MethodSignature) signature;
			validate(methodSignature, joinpoint.getArgs());
		}else if (signature instanceof FieldSignature) {//属性校验
			FieldSignature fieldSignature = (FieldSignature)signature;
		}else if (signature instanceof CatchClauseSignature) {
			CatchClauseSignature catchClauseSignature = (CatchClauseSignature)signature;
		}
	}

	private void validate(MethodSignature methodSignature, Object[] args) throws ValidationException {
		MethodSignatureValidate validator = getValidator(methodSignature);
		validator.check(args);
	}

	/**
	 * 获取方法签名的校验器；
	 * 
	 * @param methodSignature
	 * @return
	 */
	private MethodSignatureValidate getValidator(MethodSignature methodSignature) {
		String strSignature = methodSignature.toLongString();
		MethodSignatureValidate validator = validators.get(strSignature);
		if (validator == null) {
			synchronized (mutex) {
				validator = validators.get(strSignature);
				if (validator == null) {
					validator = createValidator(methodSignature);
					validators.put(strSignature, validator);
				}
			}
		}
		return validator;
	}

	/**
	 * 创建指定方法签名的校验器；
	 * 
	 * @param methodSignature
	 * @return
	 */
	private MethodSignatureValidate createValidator(MethodSignature methodSignature) {
		Method mth = methodSignature.getMethod();
		MethodSignatureValidate methodValidate = MethodValidator.create(mth);
		
		if (methodValidate == null) {
			return EmptySignatureValidator.INSTANCE;
		}
		return methodValidate;
	}

}
