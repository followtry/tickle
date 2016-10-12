package com.yonyou.tools.validation.base.validation;

import com.yonyou.tools.validation.annotation.NotEmpty;
import com.yonyou.tools.validation.base.stereotype.validation.ValidationException;

/**
 * @author haiq
 *
 */
public class NotEmptyValidator implements ConstraintValidator {

	public static final NotEmptyValidator create(NotEmpty notnull) {
		return new NotEmptyValidator(notnull.name());
	}

	public static void checkNotEmpty(String targetName, Object targetValue) {
		if (targetValue == null || "".equals(targetValue)) {
			throw new ValidationException(4001, String.format("'%s'不允许为空！", targetName));
		}
	}

	private String name;

	private NotEmptyValidator(String name) {
		this.name = name;
	}

	@Override
	public void check(Object value) throws ValidationException {
		checkNotEmpty(name, value);
	}

}
