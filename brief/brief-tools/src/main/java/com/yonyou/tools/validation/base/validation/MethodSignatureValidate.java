package com.yonyou.tools.validation.base.validation;

import com.yonyou.tools.validation.base.stereotype.validation.ValidationException;

/**
 * MethodSignatureValidate 抽象了方法签名的校验；
 * 
 * @author haiq
 *
 */
public interface MethodSignatureValidate {
	
	public static final MethodSignatureValidate NULL = new MethodSignatureValidate() {
		@Override
		public void check(Object[] args) throws ValidationException {
			// do nothing;
		}
	};

	/**
	 * 校验方法参数；
	 * 
	 * @param args
	 * @throws ValidationException
	 */
	void check(Object[] args) throws ValidationException;
}
