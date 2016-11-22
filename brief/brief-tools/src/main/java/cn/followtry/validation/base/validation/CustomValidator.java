package cn.followtry.validation.base.validation;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.BeanUtils;

import cn.followtry.validation.annotation.CustomCheck;
import cn.followtry.validation.annotation.NotEmpty;
import cn.followtry.validation.base.stereotype.validation.CustomConstraintHandle;
import cn.followtry.validation.base.stereotype.validation.ValidationException;

/**
 * 
 * @author haiq
 * @author followtry
 *
 */
public class CustomValidator extends AbstractValidator {

	private static final Object mutex = new Object();
	private static Map<Class<?>, CustomConstraintHandle> customCheckers = new ConcurrentHashMap<Class<?>, CustomConstraintHandle>();

	private CustomConstraintHandle customChecker;

	private String[] args;

	/**
	 * @param customCheck 自定义约束检查
	 * @param notNull
	 *            非空约束标注；如果指定了非空约束，则忽略 CustomCheck 中的 nullable 设置；
	 * @return 自定义校验器
	 */
	public static CustomValidator create(CustomCheck customCheck, NotEmpty notNull) {
		boolean ignoreNullableCheck = notNull != null;
		boolean nullable = customCheck.nullable();
		Class<?> customCheckerType = customCheck.handler();
		CustomConstraintHandle customChecker = getCustomCheckerInstance(customCheckerType);
		CustomValidator customValidator = new CustomValidator(customCheck.name(), customChecker, customCheck.args(),
				ignoreNullableCheck, nullable);
		return customValidator;
	}

	private static CustomConstraintHandle getCustomCheckerInstance(Class<?> checkerType) {
		CustomConstraintHandle customerChecker = customCheckers.get(checkerType);
		if (customerChecker == null) {
			synchronized (mutex) {
				customerChecker = customCheckers.get(checkerType);
				if (customerChecker == null) {
					customerChecker = createCustomChecker(checkerType);
					customCheckers.put(checkerType, customerChecker);
				}
			}
		}

		return customerChecker;
	}

	private static CustomConstraintHandle createCustomChecker(Class<?> checkerType) {
		if (!CustomConstraintHandle.class.isAssignableFrom(checkerType)) {
			throw new IllegalArgumentException("The specified Class[" + checkerType.getName()
					+ "] of CustomCheck not implement the interface[" + CustomConstraintHandle.class.getName() + "]!");
		}
		CustomConstraintHandle customerChecker = (CustomConstraintHandle) BeanUtils.instantiate(checkerType);
		return customerChecker;
	}

	private CustomValidator(String name, CustomConstraintHandle customChecker, String[] args, boolean ignoreNullableCheck,
			boolean nullable) {
		super(name, ignoreNullableCheck, nullable);
		this.customChecker = customChecker;
		this.args = args;
	}

	@Override
	protected void doCheckValue(Object arg) throws ValidationException {
		customChecker.check(name, args, arg);
	}

}
