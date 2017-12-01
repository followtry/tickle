package cn.followtry.validation.base.validation;

import cn.followtry.validation.base.common.exception.ValidationException;

/**
 * EmptyValidator 空校验器，不做任何处理；
 * 
 * @author haiq
 * @author followtry
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
