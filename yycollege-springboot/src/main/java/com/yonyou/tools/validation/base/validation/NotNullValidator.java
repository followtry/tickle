package com.yonyou.tools.validation.base.validation;

import com.yonyou.tools.validation.annotation.NotNull;
import com.yonyou.tools.validation.base.stereotype.validation.ValidationException;

/**
 * @author haiq
 *
 */
public class NotNullValidator implements ConstraintValidator {

	public static final NotNullValidator create(NotNull notnull) {
		return new NotNullValidator(notnull.name());
	}

	public static void checkNotNull(String targetName, Object targetValue) {
		if (targetValue == null) {
			throw new ValidationException(4001, String.format("'%s'不允许为空！", targetName));
		}
	}

	private String name;

	private NotNullValidator(String name) {
		this.name = name;
	}

	@Override
	public void check(Object value) throws ValidationException {
		checkNotNull(name, value);
	}

}
