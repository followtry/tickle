package com.yonyou.tools.validation.base.validation;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import com.yonyou.tools.validation.annotation.CollectionCheck;
import com.yonyou.tools.validation.annotation.CustomCheck;
import com.yonyou.tools.validation.annotation.NotNull;
import com.yonyou.tools.validation.annotation.NumericCheck;
import com.yonyou.tools.validation.annotation.NumericChecks;
import com.yonyou.tools.validation.annotation.StringCheck;
import com.yonyou.tools.validation.annotation.StringChecks;
import com.yonyou.tools.validation.base.stereotype.validation.ValidationException;

/**
 * ConstraintChainValidator 约束链校验器；
 * 
 * ConstraintChainValidator 对指定的一系列约束按照特定次序排列；
 * 
 * @author haiq
 *
 */
public class BaseTypeValidatorChain implements ConstraintValidator {

	/**
	 * 约束次序表；
	 * 
	 * key:约束类型；
	 * 
	 * value：约束类型对应的次序值；
	 * 
	 */
	private static Map<Class<?>, Integer> contraintOrders = new HashMap<Class<?>, Integer>();

	static {
		contraintOrders.put(NotNull.class, 1);

		// 数值约束和字符约束针对的是不同的数据类型；
		contraintOrders.put(NumericCheck.class, 10);
		contraintOrders.put(StringCheck.class, 20);

		// 自定义约束总是在最后；
		contraintOrders.put(CustomCheck.class, 100);
	}

	@SuppressWarnings("unused")
	private Class<?> targetType;

	private ConstraintValidator[] validatorChain;

	private BaseTypeValidatorChain(Class<?> targetType, ConstraintValidator[] validatorChain) {
		this.targetType = targetType;
		this.validatorChain = validatorChain;
	}

	@Override
	public void check(Object argValue) throws ValidationException {
		for (ConstraintValidator validator : validatorChain) {
			validator.check(argValue);
		}
	}

	/**
	 * 创建针对基本类型的校验链；
	 * 
	 * @param targetType
	 *            要校验的目标类型；
	 * @param constraints
	 *            对目标类型的约束标注列表；如果列表中的标注不适用于此类型，则忽略该标注；
	 * @return 当指定的约束标注列表中存在匹配目标类型的有效的标注，则创建并返回校验链；如果没有有效的标注，则返回 null；
	 */
	public static BaseTypeValidatorChain create(Class<?> targetType, Annotation[] constraints) {
		NotNull notNullCheck = null;
		LinkedList<NumericCheck> numericChecks = new LinkedList<NumericCheck>();
		LinkedList<StringCheck> stringChecks = new LinkedList<StringCheck>();
		LinkedList<CustomCheck> customChecks = new LinkedList<CustomCheck>();
		LinkedList<CollectionCheck> collectionChecks = new LinkedList<CollectionCheck>();

		boolean isNumericType = isNumericCheckSupported(targetType);
		boolean isStringType = isStringCheckSupported(targetType);

		//遍历参数上的注解
		for (Annotation anno : constraints) {
			if (anno instanceof NotNull) {
				// 非空约束仅设置一次；
				if (notNullCheck != null) {
					throw new IllegalArgumentException("Cann't set the NotNull constraint twice!");
				}
				notNullCheck = (NotNull) anno;
			}
			
			if (anno instanceof NumericCheck || anno instanceof NumericChecks) {
				NumericCheck[] numChecks;
				if (anno instanceof NumericCheck) {
					numChecks = new NumericCheck[]{(NumericCheck)anno};
				}else{
					numChecks = ((NumericChecks)anno).value();
				}
				for (NumericCheck numericCheck : numChecks) {
					
					// 只能针对基本数值类型进行数值约束校验；
					if (!isNumericType) {
						throw new IllegalArgumentException(
								"The target type[" + targetType.getName() + "] doesn't support the NumericCheck!");
					}
					numericChecks.add(numericCheck);
				}
			}
			
			if (anno instanceof StringCheck || anno instanceof StringChecks) {
				StringCheck[] strChecks;
				if (anno instanceof StringCheck) {
					strChecks = new StringCheck[] {(StringCheck) anno};
				}else{
					// StringChecks;
					strChecks = ((StringChecks) anno).value();
				}
				for (StringCheck stringCheckAnno : strChecks) {
					// 当字符约束的 checkToString 设置为 false 时，不能对字符类型之外的目标类型进行约束校验；
					if ((!isStringType) && (!stringCheckAnno.checkToString())) {
						throw new IllegalArgumentException("The none-string target type[" + targetType.getName()
						+ "] doesn't support the StringCheck when the 'checkToString' option of this StringCheck is set to 'false'!");
					}
					stringChecks.add(stringCheckAnno);
				}
			}
			
			if (anno instanceof CollectionCheck && ((CollectionCheck)anno).value()) {
				collectionChecks.add((CollectionCheck)anno);
			}
		
			if (anno instanceof CustomCheck) {
				customChecks.add((CustomCheck) anno);
			}
		}

		// 按顺序生产约束校验器；
		// 优先次序：非空校验 -> 数值校验 -> 文本校验 -> 自定义校验；
		LinkedList<ConstraintValidator> validatorChain = new LinkedList<ConstraintValidator>();
		// 创建非空校验；
		if (notNullCheck != null) {
			validatorChain.add(NotNullValidator.create(notNullCheck));
		}
		// 创建数值校验；
		for (NumericCheck numCheck : numericChecks) {
			NumericValidator numValidator = NumericValidator.create(targetType, numCheck, notNullCheck);
			validatorChain.add(numValidator);
		}
		// 创建文本校验；
		for (StringCheck strCheck : stringChecks) {
			StringValidator strValidator = StringValidator.create(targetType, strCheck, notNullCheck);
			validatorChain.add(strValidator);
		}
		
		for (CustomCheck customCheck : customChecks) {
			CustomValidator customValidator = CustomValidator.create(customCheck, notNullCheck);
			validatorChain.add(customValidator);
		}
		
		//创建集合校验
		for (CollectionCheck collectionCheck : collectionChecks) {
			CollectionValidator collectionValidator = CollectionValidator.create(collectionCheck);
			validatorChain.add(collectionValidator);
		}

		if (validatorChain.size() == 0) {
			return null;
		}
		return new BaseTypeValidatorChain(targetType,
				validatorChain.toArray(new ConstraintValidator[validatorChain.size()]));
	}

	private static boolean isStringCheckSupported(Class<?> targetType) {
		return TypeUtils.isStringType(targetType);
	}

	private static boolean isNumericCheckSupported(Class<?> targetType) {
		return TypeUtils.isBaseNumericeType(targetType);
	}

}
