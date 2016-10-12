package com.yonyou.tools.validation.base.validation;

import java.util.regex.Pattern;

import com.yonyou.tools.validation.annotation.NotEmpty;
import com.yonyou.tools.validation.annotation.StringCheck;
import com.yonyou.tools.validation.base.stereotype.validation.StringOperator;
import com.yonyou.tools.validation.base.stereotype.validation.ValidationException;

/**
 * 字符校验器；
 * 
 * @author haiq
 *
 */
public class StringValidator extends AbstractValidator {

	/**
	 * 创建字符校验器实例；
	 * 
	 * @param targetType 目标类型；
	 * @param stringCheck 目标上标注的字符约束；
	 * @param notnullCheck 目标上标注的非空约束；如果指定了非空约束，则忽略 StringCheck 中的 nullable 设置；
	 * @return
	 */
	public static StringValidator create(Class<?> targetType, StringCheck stringCheck, NotEmpty notnullCheck) {
		// 当指明不通过 toString 方法获取要检查的目标的文本值时，目标类型必须是文本类型；
		if ((!stringCheck.checkToString()) && (!TypeUtils.isStringType(targetType))) {
			throw new IllegalArgumentException(
					"Unsupport none-string type while the 'checkToString' option of the StringCheck constraint is false!");
		}
		boolean isPrimityStringTarget = targetType == String.class;
		StringMatcher matcher = createMatcher(stringCheck);
		boolean ignoreNullableCheck = notnullCheck != null;
		StringOperator operator = stringCheck.value();
		boolean autoTrim = StringOperator.NOT_EMPTY ==operator ? true : stringCheck.autoTrim();//对 NOT_EMPTY 操作自动执行 autoTrim；
		boolean nullable = StringOperator.NOT_EMPTY ==operator ? false : stringCheck.nullable();//对 NOT_EMPTY 操作自动执行 NOT_NULL 约束；
		return new StringValidator(stringCheck.name(), operator, matcher, autoTrim,
				isPrimityStringTarget, stringCheck.regexAlias(), ignoreNullableCheck, nullable);
	}

	private StringOperator operator;

	private StringMatcher matcher;

	private boolean autoTrim;

	private boolean isPrimityStringTarget;
	
	/**
	 * 正则表达式别名；
	 */
	private String regexAlias;

	/**
	 * 构造函数；
	 * 
	 * @param name
	 * @param ignoreNullableCheck
	 * @param nullable
	 */
	private StringValidator(String name, StringOperator operator, StringMatcher matcher, boolean autoTrim,
			boolean isPrimityStringTarget,String regexAlias, boolean ignoreNullableCheck, boolean nullable) {
		super(name, ignoreNullableCheck, nullable);
		this.operator = operator;
		this.matcher = matcher;
		this.autoTrim = autoTrim;
		this.isPrimityStringTarget = isPrimityStringTarget;
		this.regexAlias = regexAlias;
	}

	@Override
	protected void doCheckValue(Object arg) throws ValidationException {
		String text;
		if (isPrimityStringTarget) {
			text = (String) arg;
		} else {
			text = arg.toString();
		}
		if (autoTrim) {
			text = text.trim();
		}
		if (!matcher.match(text)) {
			fail(text);
		}
	}
	
	private void fail(String realValue){
		switch (operator) {
		case MIN_LEN:
			throw new ValidationException(4001, String.format("校验失败！ %s的长度不足最小长度 %s.",name,  matcher.getArgs()[0]));
		case MAX_LEN:
			throw new ValidationException(4001, String.format("校验失败！ %s的长度超出最大长度 %s.",name,  matcher.getArgs()[0]));
		case LEN_BETWEEN:
			throw new ValidationException(4001, String.format("校验失败！ %s的长度超出范围（最小 %s，最大 %s）.",name,  matcher.getArgs()[0], matcher.getArgs()[1]));
		case STARTS_WITH:
			throw new ValidationException(4001, String.format("校验失败！ %s要求必须以字符 '%s' 开头.",name,  matcher.getArgs()[0]));
		case ENDS_WITH:
			throw new ValidationException(4001, String.format("校验失败！ %s要求必须以字符 '%s' 结尾.",name,  matcher.getArgs()[0]));
		case CONTAINS:
			throw new ValidationException(4001, String.format("校验失败！ %s要求必须包含字符 '%s' .",name,  matcher.getArgs()[0]));
		case NOT_EMPTY:
			throw new ValidationException(4001, String.format("校验失败！ %s不允许为空白字符.",name));
		case REGEX:
			throw new ValidationException(4001, String.format("校验失败！ %s不符合'%s'格式'%s'.",name, regexAlias, matcher.getArgs()[0]));
		default:
			throw new IllegalArgumentException("Unsupported operator of string constraint!--" + operator.toString());
		}
	}

	// --------------------------------------------------------
	private static interface StringMatcher {
		public Object[] getArgs();
		public boolean match(String text);
	}

	private static StringMatcher createMatcher(StringCheck stringCheck) {
		StringOperator operator = stringCheck.value();
		int[] lenArgs = stringCheck.lengthArgs();
		String textArg = stringCheck.textArg();
		switch (operator) {
		case MIN_LEN:
			assertLengthArgs(operator, 1, lenArgs);
			return createMinLengthMatcher(lenArgs[0]);
		case MAX_LEN:
			assertLengthArgs(operator, 1, lenArgs);
			return createMaxLengthMatcher(lenArgs[0]);
		case LEN_BETWEEN:
			assertLengthArgs(operator, 2, lenArgs);
			return createLengthIntervalMatcher(lenArgs[0], lenArgs[1]);
		case STARTS_WITH:
			assertTextArgs(textArg);
			return createPrefixMatcher(textArg);
		case ENDS_WITH:
			assertTextArgs(textArg);
			return createSuffixMatcher(textArg);
		case CONTAINS:
			assertTextArgs(textArg);
			return createContainsMatcher(textArg);
		case NOT_EMPTY:
			return createNotEmptyMatcher();
		case REGEX:
			assertTextArgs(textArg);
			return createRegexMatcher(textArg);
		default:
			throw new IllegalArgumentException("Unsupported operator of string constraint!--" + operator.toString());
		}
	}

	/**
	 * 检查长度参数的合法性；
	 * 
	 * @param operator
	 * @param expectedArgCount
	 * @param args
	 */
	private static void assertLengthArgs(StringOperator operator, int expectedArgCount, int[] args) {
		if (args == null || args.length == 0) {
			throw new IllegalArgumentException("Miss the length arguments!");
		}
		if (args.length != expectedArgCount) {
			throw new IllegalArgumentException(String.format(
					"The %s constraint of string requires the only %s length argument!", operator, expectedArgCount));
		}
	}

	/**
	 * 检查文本参数的合法性；
	 * 
	 * @param operator
	 * @param expectedArgCount
	 * @param args
	 */
	private static void assertTextArgs(String textArg) {
		if (textArg == null || textArg.length() == 0) {
			throw new IllegalArgumentException("Miss the text arguments!");
		}
	}

	private static StringMatcher createMaxLengthMatcher(int maxLength) {
		return new StringMatcher() {
			@Override
			public boolean match(String text) {
				return text.length() <= maxLength;
			}

			@Override
			public Object[] getArgs() {
				return new Object[] {maxLength};
			}
		};
	}

	private static StringMatcher createMinLengthMatcher(int minLength) {
		return new StringMatcher() {
			@Override
			public boolean match(String text) {
				return text.length() >= minLength;
			}
			@Override
			public Object[] getArgs() {
				return new Object[] {minLength};
			}
		};
	}

	/**
	 * 创建长度区间的校验器；
	 * 
	 * @param minLength
	 * @return
	 */
	private static StringMatcher createLengthIntervalMatcher(int minLength, int maxLength) {
		int min = minLength > maxLength ? maxLength : minLength;
		int max = minLength > maxLength ? minLength : maxLength;
		return new StringMatcher() {
			@Override
			public boolean match(String text) {
				return text.length() >= min && text.length() <= max;
			}
			@Override
			public Object[] getArgs() {
				return new Object[] {min, max};
			}
		};
	}

	private static StringMatcher createPrefixMatcher(String prefix) {
		return new StringMatcher() {
			@Override
			public boolean match(String text) {
				return text.startsWith(prefix);
			}
			@Override
			public Object[] getArgs() {
				return new Object[] {prefix};
			}
		};
	}

	private static StringMatcher createSuffixMatcher(String suffix) {
		return new StringMatcher() {
			@Override
			public boolean match(String text) {
				return text.endsWith(suffix);
			}
			@Override
			public Object[] getArgs() {
				return new Object[] {suffix};
			}
		};
	}

	private static StringMatcher createContainsMatcher(String section) {
		return new StringMatcher() {
			@Override
			public boolean match(String text) {
				return text.contains(section);
			}
			@Override
			public Object[] getArgs() {
				return new Object[] {section};
			}
		};
	}
	
	private static StringMatcher createNotEmptyMatcher() {
		return new StringMatcher() {
			@Override
			public boolean match(String text) {
				return text.length() > 0;
			}
			@Override
			public Object[] getArgs() {
				return new Object[] {}; 
			}
		};
	}

	private static StringMatcher createRegexMatcher(String regex) {
		Pattern pattern = Pattern.compile(regex);
		return new StringMatcher() {
			@Override
			public boolean match(String text) {
				return pattern.matcher(text).matches();
			}
			@Override
			public Object[] getArgs() {
				return new Object[] {regex};
			}
		};
	}
}
