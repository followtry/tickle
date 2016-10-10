package com.yonyou.tools.validation.base.common.exception;

public class ResponseResult {

	private boolean success;

	private Object data;

	private ErrorMessage error;
	
	private ResponseResult(){
		
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public ErrorMessage getError() {
		return error;
	}

	public void setError(ErrorMessage error) {
		this.error = error;
	}
	
	public static ResponseResult createSuccessResult(Object data){
		ResponseResult resonseResult = new ResponseResult();
		resonseResult.setSuccess(true);
		resonseResult.setData(data);
		return resonseResult;
	}
	
	public static ResponseResult createFailureResult(int code, String message){
		ErrorMessage errorMessage = new ErrorMessage(code, message);
		return createFailureResult(errorMessage);
	}
	
	public static ResponseResult createFailureResult(ErrorMessage errorMessage){
		ResponseResult resonseResult = new ResponseResult();
		resonseResult.setSuccess(false);
		resonseResult.setError(errorMessage);
		return resonseResult;
	}
}
