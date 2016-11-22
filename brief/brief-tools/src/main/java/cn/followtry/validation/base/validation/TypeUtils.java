package cn.followtry.validation.base.validation;

import java.math.BigDecimal;
import java.math.BigInteger;

public abstract class TypeUtils {
	/**
	 * 布尔类型；
	 */
	private static Class<?>[] BOOLEAN_TYPES = { boolean.class, Boolean.class };

	/**
	 * 数值类型；
	 */
	private static Class<?>[] BASE_NUMERIC_TYPES = { byte.class, char.class, short.class, int.class, long.class,
			float.class, double.class, Byte.class, Character.class, Short.class, Integer.class, Long.class, Float.class,
			Double.class };
	/**
	 * 大数值类型；
	 */
	private static Class<?>[] BIG_NUMERIC_TYPES = { BigInteger.class, BigDecimal.class };

	/**
	 * 字符类型；
	 */
	private static Class<?>[] STRING_TYPES = { String.class, StringBuilder.class, StringBuilder.class,
			CharSequence.class };

	private static Class<?>[] BASE_TYPES;

	static {
		BASE_TYPES = new Class<?>[BOOLEAN_TYPES.length + BASE_NUMERIC_TYPES.length + BIG_NUMERIC_TYPES.length
				+ STRING_TYPES.length];

		int startIndex = 0;
		System.arraycopy(BOOLEAN_TYPES, 0, BASE_TYPES, startIndex, BOOLEAN_TYPES.length);

		startIndex += BOOLEAN_TYPES.length;
		System.arraycopy(BASE_NUMERIC_TYPES, 0, BASE_TYPES, startIndex, BASE_NUMERIC_TYPES.length);

		startIndex += BASE_NUMERIC_TYPES.length;
		System.arraycopy(BIG_NUMERIC_TYPES, 0, BASE_TYPES, startIndex, BIG_NUMERIC_TYPES.length);

		startIndex += BIG_NUMERIC_TYPES.length;
		System.arraycopy(STRING_TYPES, 0, BASE_TYPES, startIndex, STRING_TYPES.length);
	}

	/**
	 * 是否是基本类型；
	 * 
	 * 基本数据类型包括：java基本类型及其包装类， BigInteger, BigDecimal, String 及其衍生类
	 * StringBuilder, StringBuffer, CharSequence；
	 * 
	 * @param clazz class类型
	 * @return 是不是基本类型
	 */
	public static boolean isBaseType(Class<?> clazz) {
		for (Class<?> cls : BASE_TYPES) {
			if (clazz == cls) {
				return true;
			}
		}
		return false;
	}

	public static Class<?>[] getBaseNumericeTypes() {
		Class<?>[] types = new Class<?>[BASE_NUMERIC_TYPES.length];
		System.arraycopy(BASE_NUMERIC_TYPES, 0, types, 0, BASE_NUMERIC_TYPES.length);
		return types;
	}

	public static Class<?>[] getStringTypes() {
		Class<?>[] types = new Class<?>[STRING_TYPES.length];
		System.arraycopy(STRING_TYPES, 0, types, 0, STRING_TYPES.length);
		return types;
	}

	/**
	 * 是否是基本数值类型；
	 * 
	 * 基本数值类型包括：byte, char, short, int, long, float, double 以及对应的包装类型；
	 * 
	 * @param clazz class类型
	 * @return 是不是基本的数字类型
	 */
	public static boolean isBaseNumericeType(Class<?> clazz) {
		for (Class<?> cls : BASE_NUMERIC_TYPES) {
			if (clazz == cls) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 是否是文本类型；
	 * 
	 * 文本类型包括：String、StringBuilder, StringBuffer, CharSequence；
	 * 
	 * @param clazz
	 *            class类型
	 * @return 返回是否为文本类型的bool值
	 */
	public static boolean isStringType(Class<?> clazz) {
		for (Class<?> cls : STRING_TYPES) {
			if (clazz == cls) {
				return true;
			}
		}
		return false;
	}

	private TypeUtils() {
	}
}
