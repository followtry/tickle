package cn.followtry.validation.base.common.exception;

/**
 * 错误代码； 
 * @author followtry
 */
public enum ErrorCode {
	
	/** 未预期的异常 */
	UNEXPECTED(5000, "未预期的异常！");
	

	private int value;

	private String description;

	private ErrorCode(int value, String description) {
		this.value = value;
		this.description = description;
	}

	public int getValue() {
		return value;
	}

	public String getDescription() {
		return description;
	}

}