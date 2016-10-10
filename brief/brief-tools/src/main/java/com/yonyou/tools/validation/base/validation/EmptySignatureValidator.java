package com.yonyou.tools.validation.base.validation;

import com.yonyou.tools.validation.base.stereotype.validation.ValidationException;

/**
 * EmptySignatureValidator 空校验器，不做任何处理；
 * 
 * @author haiq
 *
 */
public class EmptySignatureValidator implements MethodSignatureValidate{
	
	public static final EmptySignatureValidator INSTANCE = new EmptySignatureValidator();
	
	private EmptySignatureValidator() {
	}

	@Override
	public void check(Object[] args) throws ValidationException {
		//do nothing;
	}

}
