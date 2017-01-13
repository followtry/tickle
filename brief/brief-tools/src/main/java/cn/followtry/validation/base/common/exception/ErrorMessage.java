/**
 * 
 */
package cn.followtry.validation.base.common.exception;

/**
 * 错误信息试题
 * 
 * @author 
 *		followtry
 * @since 
 *		0.0.2
 * @version 
 * 		0.0.2
 */
public class ErrorMessage {
	
	private int errorCode;

	private String errorMessage;

	public ErrorMessage() {

	}

	public ErrorMessage(int errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
