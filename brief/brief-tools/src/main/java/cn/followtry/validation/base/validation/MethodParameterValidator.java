package cn.followtry.validation.base.validation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Parameter;
import java.util.LinkedList;

import cn.followtry.validation.base.stereotype.validation.ValidationException;

/**
 * MethodParameterValidator 方法参数的校验器；
 * 
 * @author haiq
 * @author followtry
 */
public class MethodParameterValidator implements ConstraintValidator {

	private Parameter param;

	private ConstraintValidator[] validators;

	/**
	 * 创建一个参数校验器实例；
	 * 
	 * @param param
	 *            校验的参数；
	 * @param validators
	 *            与此参数相关的校验器列表；
	 */
	private MethodParameterValidator(Parameter param, ConstraintValidator[] validators) {
		this.param = param;
		this.validators = validators;
	}

	/**
	 * 创建参数校验器；
	 * 
	 * @param param 参数
	 * @return 方法参数校验器
	 */
	public static MethodParameterValidator create(Parameter param) {
		//参数上的校验链由参数本身声明的约束校验和参数类型本身声明的约束校验组成；
		//优先执行参数本身声明的约束校验；
		LinkedList<ConstraintValidator> validators = new LinkedList<ConstraintValidator>();
		Class<?> paramType = param.getType();

		// 对参数上声明的约束进行校验；之后再对参数类型上声明的约束进行校验；
		Annotation[] annotations = param.getAnnotations();
		ConstraintValidator paramValidator = BaseTypeValidatorChain.create(paramType, annotations);
		if (paramValidator != null) {
			validators.add(paramValidator);
		}

		BeanValidator beanValidator = BeanValidator.create(paramType);
		if (beanValidator != null) {
			validators.add(beanValidator);
		}

		return new MethodParameterValidator(param, validators.toArray(new ConstraintValidator[validators.size()]));
	}

	@Override
	public void check(Object arg) throws ValidationException {
		for (ConstraintValidator validator : validators) {
			validator.check(arg);
		}
	}

	public Parameter getParam() {
		return param;
	}

}
