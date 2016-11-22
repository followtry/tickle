package cn.followtry.validation.base.validation;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import cn.followtry.validation.base.common.exception.ValidationException;

/**
 * 方法校验器；
 * 
 * @author haiq
 * @author followtry
 *
 */
public class MethodValidator implements MethodSignatureValidate {
	
	
	/**
	 * 创建指定方法的校验器；
	 * 
	 * @param method 校验的目标方法
	 * @return 返回方法的约束校验器；如果指定的是无参方法，则返回 null；
	 */
	public static MethodValidator create(Method method){
		Parameter[] params = method.getParameters();
		if (params.length == 0) {
			return null;
		}

		ConstraintValidator[] paramValidators = new ConstraintValidator[params.length];
		for (int i = 0; i < params.length; i++) {
			paramValidators[i] = MethodParameterValidator.create(params[i]);
		}

		return new MethodValidator(method, paramValidators);
	}
	

	@SuppressWarnings("unused")
	private Method method;

	private ConstraintValidator[] parameterValidators;

	/**
	 * 创建一个方法校验器实例；
	 * 
	 * @param methodSignature
	 *            方法签名；
	 * @param parameterValidators
	 *            方法参数的校验器列表；必须与参数的个数及顺序一致；
	 */
	private MethodValidator(Method method, ConstraintValidator[] parameterValidators) {
		this.method = method;
		this.parameterValidators = parameterValidators;
	}

	@Override
	public void check(Object[] args) throws ValidationException {
		for (int i = 0; i < args.length; i++) {
			parameterValidators[i].check(args[i]);
		}
	}

}
