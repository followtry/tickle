package com.yonyou.tools.validation.base.stereotype.validation;

/**
 * 字符串操作符；
 * 
 * @author haiq
 *
 */
public enum StringOperator {
	
	/**
	 * 最小长度；即长度大于等于该值；
	 */
	MIN_LEN,
	
	/**
	 * 最大长度；即长度小于等于该值；
	 */
	MAX_LEN,
	
	/**
	 * 长度介于两值区间（闭区间）；
	 */
	LEN_BETWEEN,
	
	/**
	 * 以特定字符开头；
	 */
	STARTS_WITH,
	
	/**
	 * 以特定字符结尾；
	 */
	ENDS_WITH,
	
	/**
	 * 包含特定字符；
	 */
	CONTAINS,
	
	/**
	 * 非空白字符；
	 * 
	 * 采用此操作符则自动包括了对 NOT_NULL 的校验和对字符的 autoTrim 处理；
	 */
	NOT_EMPTY,
	
	/**
	 * 正则表达式匹配；
	 */
	REGEX
	
	
}
