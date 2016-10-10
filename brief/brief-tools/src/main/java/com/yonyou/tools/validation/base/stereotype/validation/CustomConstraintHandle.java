package com.yonyou.tools.validation.base.stereotype.validation;

/**
 * {@link CustomConstraintHandle} 是自定义的约束校验器的需要实现的接口；
 * 
 * @author haiq
 *
 */
public interface CustomConstraintHandle {

	/**
	 * 执行约束校验；
	 * 
	 * @param value
	 * @param args
	 */
	/**
	 * @param name
	 *            约束目标的名称；
	 * @param args
	 *            约束标注的校验参数；
	 * @param value
	 *            约束标注目标的值；
	 */
	public void check(String name, String[] args, Object value);

}
