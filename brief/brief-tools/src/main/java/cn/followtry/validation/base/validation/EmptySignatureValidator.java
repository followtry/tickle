package cn.followtry.validation.base.validation;

import cn.followtry.validation.base.stereotype.validation.ValidationException;

/**
 * EmptySignatureValidator 空校验器，不做任何处理；
 * 
 * @author haiq
 * @author followtry
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
