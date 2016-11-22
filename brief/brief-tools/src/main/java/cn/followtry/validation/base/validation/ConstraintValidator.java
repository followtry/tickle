package cn.followtry.validation.base.validation;

import cn.followtry.validation.base.stereotype.validation.ValidationException;

/**
 * 约束校验器；
 * 
 * @author haiq
 * @author followtry
 *
 */
public interface ConstraintValidator {

	/**
	 * 校验指定对象是否满足约束条件；
	 * 
	 * 如果不满足，将抛出 ValidationException 异常；
	 * 
	 * @param value 要校验的目标的值；
	 * @throws ValidationException 校验异常
	 */
	public void check(Object value) throws ValidationException;

}
