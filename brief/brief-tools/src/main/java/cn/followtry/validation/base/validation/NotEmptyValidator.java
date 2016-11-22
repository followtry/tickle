package cn.followtry.validation.base.validation;

import cn.followtry.validation.annotation.NotEmpty;
import cn.followtry.validation.base.stereotype.validation.ValidationException;

/**
 * 非空约束校验器
 * 
 * @author followtry
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
