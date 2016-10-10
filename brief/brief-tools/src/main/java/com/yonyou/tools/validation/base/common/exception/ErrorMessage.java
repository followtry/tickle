/**
 * 
 */
package com.yonyou.tools.validation.base.common.exception;

/**
 * @author jingzz
 * @time 2016年10月10日 上午11:13:01
 * @name brief-tools/com.yonyou.tools.validation.base.common.exception.ErrorMessage
 * @since 2016年10月10日 上午11:13:01
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
