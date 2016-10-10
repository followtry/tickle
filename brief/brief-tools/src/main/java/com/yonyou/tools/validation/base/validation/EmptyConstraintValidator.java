package com.yonyou.tools.validation.base.validation;

import com.yonyou.tools.validation.base.stereotype.validation.ValidationException;

/**
 * EmptyValidator 空校验器，不做任何处理；
 * 
 * @author haiq
 *
 */
public class EmptyConstraintValidator implements ConstraintValidator{
	
	public static final EmptyConstraintValidator INSTANCE = new EmptyConstraintValidator();
	
	private EmptyConstraintValidator() {
	}

	@Override
	public void check(Object arg) throws ValidationException {
		//do nothing;
	}

}
