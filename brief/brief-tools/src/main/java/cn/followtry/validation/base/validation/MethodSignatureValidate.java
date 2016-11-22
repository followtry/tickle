package cn.followtry.validation.base.validation;

import cn.followtry.validation.base.stereotype.validation.ValidationException;

/**
 * MethodSignatureValidate 抽象了方法签名的校验；
 * 
 * @author followtry
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
	 * @param args 检查的参数
	 * @throws ValidationException  校验异常
	 */
	void check(Object[] args) throws ValidationException;
}
