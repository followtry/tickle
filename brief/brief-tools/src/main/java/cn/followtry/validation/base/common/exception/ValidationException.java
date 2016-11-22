package cn.followtry.validation.base.common.exception;

/**
 * 数据校验异常；
 * 
 * @author haiq
 *
 */
public class ValidationException extends BusinessException {

	private static final long serialVersionUID = 7492336331936699248L;

	public ValidationException() {
	}
	
	public ValidationException(ErrorCode errorCode) {
		super(errorCode);
	}
	
	public ValidationException(int errorCode, String message) {
		super(errorCode, message);
	}
	
	public ValidationException(int errorCode, String message, Throwable cause) {
		super(errorCode, message, cause);
	}
	
}
