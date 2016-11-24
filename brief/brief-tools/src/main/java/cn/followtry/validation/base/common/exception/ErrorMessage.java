/**
 * 
 */
package cn.followtry.validation.base.common.exception;

/**
 * @author followtry 作者
 * @since 2016年10月10日 上午11:13:01  开始时间
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
