package cn.followtry.validation.base.validation;

import java.util.Collection;

import org.springframework.util.CollectionUtils;

import cn.followtry.validation.annotation.CollectionCheck;
import cn.followtry.validation.base.common.exception.ValidationException;

/**
 * @author haiq
 * @author followtry
 * 
 */
public class CollectionValidator implements ConstraintValidator {

	public static final CollectionValidator create(CollectionCheck collectionCheck) {
		return new CollectionValidator(collectionCheck.name(),collectionCheck.value());
	}

	public static void checkCollection(String targetName,Boolean check,Object targetValue) {
		if (!check) {
			return;
		}else if (targetValue == null) {
			throw new ValidationException(4001, String.format("集合'%s'不允许为null！", targetName));
		}else if (targetValue instanceof Collection) {
			if (CollectionUtils.isEmpty((Collection<?>) targetValue)) {
				throw new ValidationException(4001, String.format("'%s'不允许为空集合！", targetName));
			}
		}
	}

	private String name;
	
	private boolean check;

	private CollectionValidator(String name,boolean value) {
		this.name = name;
		this.check = value;
	}

	@Override
	public void check(Object value) throws ValidationException {
		checkCollection(name,check, value);
	}

}
