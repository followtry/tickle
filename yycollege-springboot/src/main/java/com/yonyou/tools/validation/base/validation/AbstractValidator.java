package com.yonyou.tools.validation.base.validation;

import com.yonyou.tools.validation.base.stereotype.validation.ValidationException;

/**
 * @author haiq
 *
 */
public abstract class AbstractValidator implements ConstraintValidator {

	protected final String name;

	private boolean ignoreNullableCheck;

	private boolean nullable;

	/**
	 * 实例化 AbstractBaseTypeValidator；
	 * 
	 * @param name
	 *            校验目标名称，用于在输出错误信息时提示；
	 * 
	 * @param ignoreNullableCheck
	 *            是否忽略空值校验；当在同一目标上已经应用了 NotNull 约束，则可以把此属性设置 true，不必重复校验空值；
	 * @param nullable
	 *            针对当前约束而设置的空值约束；实际校验效果会被同一目标上已经应用的 NotNull 约束覆盖；
	 */
	protected AbstractValidator(String name, boolean ignoreNullableCheck, boolean nullable) {
		this.name = name;
		this.ignoreNullableCheck = ignoreNullableCheck;
		this.nullable = nullable;
	}

	@Override
	public void check(Object value) throws ValidationException {
		if (!ignoreNullableCheck) {
			// 执行空检查；
			if (nullable && value == null) {
				// 可空时对空值不做任何后续校验；
				return;
			}
			// 不可为空；
			NotNullValidator.checkNotNull(name, value);
		}
		doCheckValue(value);
	}

	protected abstract void doCheckValue(Object value) throws ValidationException;

}
