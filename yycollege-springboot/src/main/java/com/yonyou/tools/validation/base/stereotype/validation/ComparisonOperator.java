package com.yonyou.tools.validation.base.stereotype.validation;

/**
 * 比较运算符；
 * 
 * @author haiq
 *
 */
public enum ComparisonOperator {
	
	/**
	 * 大于；
	 */
	GT(1),
	
	/**
	 * 大于等于；
	 */
	GE(1),
	
	/**
	 * 小于；
	 */
	LT(1),
	
	/**
	 * 小于等于；
	 */
	LE(1),
	
	/**
	 * 等于；
	 */
	EQ(1),
	
	/**
	 * 不等于；
	 */
	NE(1),
	
	/**
	 * 闭区间； 
	 */
	INTERVAL_CLOSED(2),
	
	/**
	 * 开区间；
	 */
	INTERVAL_OPEN(2),
	
	/**
	 * 左开右闭区间；
	 */
	INTERVAL_L_OPEN_R_CLOSED(2),
	
	/**
	 * 左闭右开区间；
	 */
	INTERVAL_L_CLOSE_R_OPEN(2);
	
	/**
	 * 操作数的个数；
	 */
	private int operandCount;
	
	public int getOperandCount() {
		return operandCount;
	}
	
	private ComparisonOperator(int operandCount) {
		this.operandCount = operandCount;
	}
}
