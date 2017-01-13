package cn.followtry.validation.base.common.exception;

/**
 * 数据校验异常；
 * 
 * @author haiq
 * @author followtry
 *
 */
public class ValidationException extends RuntimeException {

	private static final long serialVersionUID = 7492336331936699248L;
	
	private int errorCode;			//错误代码
	
	private String errorMessage;	//错误详细信息

	public ValidationException() {
	}
	
	public int getErrorCode() {
		return errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public ValidationException(ErrorCode errorCode) {
		super(errorCode.getDescription());
		this.errorCode = errorCode.getValue();
		this.errorMessage = errorCode.getDescription();
	}
	
	public ValidationException(int errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
		this.errorMessage = message;
	}
	
	public ValidationException(int errorCode, String message, Throwable cause) {
		super(message, cause);
		this.errorCode = errorCode;
		this.errorMessage = message;
	}
	
}
