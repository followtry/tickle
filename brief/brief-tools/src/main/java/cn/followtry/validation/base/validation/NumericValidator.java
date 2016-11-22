package cn.followtry.validation.base.validation;

import java.util.Arrays;

import cn.followtry.validation.annotation.NotEmpty;
import cn.followtry.validation.annotation.NumericCheck;
import cn.followtry.validation.base.stereotype.validation.ComparisonOperator;
import cn.followtry.validation.base.stereotype.validation.ValidationException;

/**
 * NumericValidator 数值校验器；
 * 
 * @author followtry
 *
 */
public class NumericValidator extends AbstractValidator {

	protected ComparisonOperator operator;

	private NumericComparator comparator;

	private Object operand1;

	private Object operand2;

	/**
	 * 创建数值校验器实例；
	 * 
	 * @param targetType 
	 * 			目标类型
	 * @param numericCheck
	 *            数值约束标注；
	 * @param notnullCheck
	 *            非空约束标注；如果指定了非空约束，则忽略 NumericCheck 中的 nullable 设置；
	 * @return 数值校验器
	 */
	public static NumericValidator create(Class<?> targetType, NumericCheck numericCheck, NotEmpty notnullCheck) {
		if (numericCheck.args().length < numericCheck.value().getOperandCount()) {
			throw new IllegalArgumentException(
					"The number of NumericCheck's args is less than the required operand number of operator["
							+ numericCheck.value() + "]!");
		}

		boolean ignoreNullableCheck = notnullCheck != null;
		boolean nullable = numericCheck.nullable();

		NumericComparator comparator = createComparator(targetType, numericCheck.value(), numericCheck.args());

		return new NumericValidator(numericCheck.name(), comparator, numericCheck.value(), ignoreNullableCheck,
				nullable);
	}

	/**
	 * NumericValidator 构造函数；
	 * @param name 指定显式名称
	 * @param comparator 数值比较器
	 * @param operator 比较运算符
	 * @param ignoreNullableCheck 指定对忽略null值的检查
	 * @param nullable 空
	 */
	protected NumericValidator(String name, NumericComparator comparator, ComparisonOperator operator,
			boolean ignoreNullableCheck, boolean nullable) {
		super(name, ignoreNullableCheck, nullable);
		this.comparator = comparator;
		this.operator = operator;
		this.operand1 = comparator.getOperand1();
		this.operand2 = comparator.getOperand2();
	}

	@Override
	protected void doCheckValue(Object arg) throws ValidationException {
		if (!comparator.check(arg)) {
			fail(arg, operand1, operand2);
		}
	}

	protected void fail(Object arg, Object operand1, Object operand2) {
		switch (operator) {
		case GT:
			throw new ValidationException(4001, String.format("校验失败！'%s'的值为 %s，要求必须大于 %s ！", name, arg, operand1));
		case GE:
			throw new ValidationException(4001, String.format("校验失败！'%s'的值为 %s，要求必须大于等于 %s ！", name, arg, operand1));
		case LT:
			throw new ValidationException(4001, String.format("校验失败！'%s'的值为 %s，要求必须小于 %s ！", name, arg, operand1));
		case LE:
			throw new ValidationException(4001, String.format("校验失败！'%s'的值为 %s，要求必须小于等于 %s ！", name, arg, operand1));
		case EQ:
			throw new ValidationException(4001, String.format("校验失败！'%s'的值为 %s，要求必须等于 %s ！", name, arg, operand1));
		case NE:
			throw new ValidationException(4001, String.format("校验失败！'%s'的值为 %s，要求必须不等于 %s ！", name, arg, operand1));
		case INTERVAL_OPEN:
			throw new ValidationException(4001,
					String.format("校验失败！'%s'的值为 %s，要求必须大于 %s 且小于 %s ！", name, arg, operand1, operand2));
		case INTERVAL_CLOSED:
			throw new ValidationException(4001,
					String.format("校验失败！'%s'的值为 %s，要求必须大于等于 %s 且小于等于 %s ！", name, arg, operand1, operand2));
		case INTERVAL_L_OPEN_R_CLOSED:
			throw new ValidationException(4001,
					String.format("校验失败！'%s'的值为 %s，要求必须大于 %s 且小于等于 %s ！", name, arg, operand1, operand2));
		case INTERVAL_L_CLOSE_R_OPEN:
			throw new ValidationException(4001,
					String.format("校验失败！'%s'的值为 %s，要求必须大于等于 %s 且小于 %s ！", name, arg, operand1, operand2));
		default:
			throw new UnsupportedOperationException("Unsupported Operator [" + operator + "]!");
		}

	}

	private static byte[] getByteOperand(double[] doubleOperand) {
		byte[] operand = new byte[doubleOperand.length];
		for (int i = 0; i < doubleOperand.length; i++) {
			if (doubleOperand[i] < Byte.MIN_VALUE || doubleOperand[i] > Byte.MAX_VALUE) {
				throw new IllegalArgumentException("The operand overflow the range of target data type!");
			}
			operand[i] = (byte) doubleOperand[i];
		}
		Arrays.sort(operand);
		return operand;
	}

	private static char[] getCharOperand(double[] doubleOperand) {
		char[] operand = new char[doubleOperand.length];
		for (int i = 0; i < doubleOperand.length; i++) {
			if (doubleOperand[i] < Character.MIN_VALUE || doubleOperand[i] > Character.MAX_VALUE) {
				throw new IllegalArgumentException("The operand overflow the range of target data type!");
			}
			operand[i] = (char) doubleOperand[i];
		}
		Arrays.sort(operand);
		return operand;
	}

	private static short[] getShortOperand(double[] doubleOperand) {
		short[] operand = new short[doubleOperand.length];
		for (int i = 0; i < doubleOperand.length; i++) {
			if (doubleOperand[i] < Short.MIN_VALUE || doubleOperand[i] > Short.MAX_VALUE) {
				throw new IllegalArgumentException("The operand overflow the range of target data type!");
			}
			operand[i] = (short) doubleOperand[i];
		}
		Arrays.sort(operand);
		return operand;
	}

	private static int[] getIntegerOperand(double[] doubleOperand) {
		int[] operand = new int[doubleOperand.length];
		for (int i = 0; i < doubleOperand.length; i++) {
			if (doubleOperand[i] < Integer.MIN_VALUE || doubleOperand[i] > Integer.MAX_VALUE) {
				throw new IllegalArgumentException("The operand overflow the range of target data type!");
			}
			operand[i] = (int) doubleOperand[i];
		}
		Arrays.sort(operand);
		return operand;
	}

	private static long[] getLongOperand(double[] doubleOperand) {
		long[] operand = new long[doubleOperand.length];
		for (int i = 0; i < doubleOperand.length; i++) {
			if (doubleOperand[i] < Long.MIN_VALUE || doubleOperand[i] > Long.MAX_VALUE) {
				throw new IllegalArgumentException("The operand overflow the range of target data type!");
			}
			operand[i] = (long) doubleOperand[i];
		}
		Arrays.sort(operand);
		return operand;
	}

	private static float[] getFloatOperand(double[] doubleOperand) {
		float[] operand = new float[doubleOperand.length];
		for (int i = 0; i < doubleOperand.length; i++) {
			if (doubleOperand[i] < Float.MIN_VALUE || doubleOperand[i] > Float.MAX_VALUE) {
				throw new IllegalArgumentException("The operand overflow the range of target data type!");
			}
			operand[i] = (float) doubleOperand[i];
		}
		Arrays.sort(operand);
		return operand;
	}

	private static double[] getDoubleOperand(double[] doubleOperand) {
		double[] operand = new double[doubleOperand.length];
		for (int i = 0; i < doubleOperand.length; i++) {
			// if (doubleOperand[i] < Double.MIN_VALUE || doubleOperand[i] >
			// Double.MAX_VALUE) {
			// throw new IllegalArgumentException("The operand overflow the
			// range of target data type!");
			// }
			operand[i] = (double) doubleOperand[i];
		}
		Arrays.sort(operand);
		return operand;
	}

	// --------------------------------------------------------------------------------------

	/**
	 * @author followtry
	 *
	 */
	private static interface NumericComparator {

		public Object getOperand1();

		public Object getOperand2();

		public boolean check(Object arg);

	}

	private static NumericComparator createComparator(Class<?> type, ComparisonOperator operator, double[] args) {
		switch (operator) {
		case GT:
			if (byte.class == type || Byte.class == type) {
				return createByteGTComparator(getByteOperand(args)[0]);
			}
			if (char.class == type || Character.class == type) {
				return createCharGTComparator(getCharOperand(args)[0]);
			}
			if (short.class == type || Short.class == type) {
				return createShortGTComparator(getShortOperand(args)[0]);
			}
			if (int.class == type || Integer.class == type) {
				return createIntegerGTComparator(getIntegerOperand(args)[0]);
			}
			if (long.class == type || Long.class == type) {
				return createLongGTComparator(getLongOperand(args)[0]);
			}
			if (float.class == type || Float.class == type) {
				return createFloatGTComparator(getFloatOperand(args)[0]);
			}
			if (double.class == type || Double.class == type) {
				return createDoubleGTComparator(getDoubleOperand(args)[0]);
			}
			break;
		case GE:
			if (byte.class == type || Byte.class == type) {
				return createByteGEComparator(getByteOperand(args)[0]);
			}
			if (char.class == type || Character.class == type) {
				return createCharGEComparator(getCharOperand(args)[0]);
			}
			if (short.class == type || Short.class == type) {
				return createShortGEComparator(getShortOperand(args)[0]);
			}
			if (int.class == type || Integer.class == type) {
				return createIntegerGEComparator(getIntegerOperand(args)[0]);
			}
			if (long.class == type || Long.class == type) {
				return createLongGEComparator(getLongOperand(args)[0]);
			}
			if (float.class == type || Float.class == type) {
				return createFloatGEComparator(getFloatOperand(args)[0]);
			}
			if (double.class == type || Double.class == type) {
				return createDoubleGEComparator(getDoubleOperand(args)[0]);
			}
			break;
		case LT:
			if (byte.class == type || Byte.class == type) {
				return createByteLTComparator(getByteOperand(args)[0]);
			}
			if (char.class == type || Character.class == type) {
				return createCharLTComparator(getCharOperand(args)[0]);
			}
			if (short.class == type || Short.class == type) {
				return createShortLTComparator(getShortOperand(args)[0]);
			}
			if (int.class == type || Integer.class == type) {
				return createIntegerLTComparator(getIntegerOperand(args)[0]);
			}
			if (long.class == type || Long.class == type) {
				return createLongLTComparator(getLongOperand(args)[0]);
			}
			if (float.class == type || Float.class == type) {
				return createFloatLTComparator(getFloatOperand(args)[0]);
			}
			if (double.class == type || Double.class == type) {
				return createDoubleLTComparator(getDoubleOperand(args)[0]);
			}
			break;
		case LE:
			if (byte.class == type || Byte.class == type) {
				return createByteLEComparator(getByteOperand(args)[0]);
			}
			if (char.class == type || Character.class == type) {
				return createCharLEComparator(getCharOperand(args)[0]);
			}
			if (short.class == type || Short.class == type) {
				return createShortLEComparator(getShortOperand(args)[0]);
			}
			if (int.class == type || Integer.class == type) {
				return createIntegerLEComparator(getIntegerOperand(args)[0]);
			}
			if (long.class == type || Long.class == type) {
				return createLongLEComparator(getLongOperand(args)[0]);
			}
			if (float.class == type || Float.class == type) {
				return createFloatLEComparator(getFloatOperand(args)[0]);
			}
			if (double.class == type || Double.class == type) {
				return createDoubleLEComparator(getDoubleOperand(args)[0]);
			}
			break;
		case EQ:
			if (byte.class == type || Byte.class == type) {
				return createByteEQComparator(getByteOperand(args)[0]);
			}
			if (char.class == type || Character.class == type) {
				return createCharEQComparator(getCharOperand(args)[0]);
			}
			if (short.class == type || Short.class == type) {
				return createShortEQComparator(getShortOperand(args)[0]);
			}
			if (int.class == type || Integer.class == type) {
				return createIntegerEQComparator(getIntegerOperand(args)[0]);
			}
			if (long.class == type || Long.class == type) {
				return createLongEQComparator(getLongOperand(args)[0]);
			}
			if (float.class == type || Float.class == type) {
				return createFloatEQComparator(getFloatOperand(args)[0]);
			}
			if (double.class == type || Double.class == type) {
				return createDoubleEQComparator(getDoubleOperand(args)[0]);
			}
			break;
		case NE:
			if (byte.class == type || Byte.class == type) {
				return createByteNEComparator(getByteOperand(args)[0]);
			}
			if (char.class == type || Character.class == type) {
				return createCharNEComparator(getCharOperand(args)[0]);
			}
			if (short.class == type || Short.class == type) {
				return createShortNEComparator(getShortOperand(args)[0]);
			}
			if (int.class == type || Integer.class == type) {
				return createIntegerNEComparator(getIntegerOperand(args)[0]);
			}
			if (long.class == type || Long.class == type) {
				return createLongNEComparator(getLongOperand(args)[0]);
			}
			if (float.class == type || Float.class == type) {
				return createFloatNEComparator(getFloatOperand(args)[0]);
			}
			if (double.class == type || Double.class == type) {
				return createDoubleNEComparator(getDoubleOperand(args)[0]);
			}
			break;
		case INTERVAL_OPEN:
			if (byte.class == type || Byte.class == type) {
				byte[] operand = getByteOperand(args);
				return createByteIntervalOpenComparator(operand[0], operand[1]);
			}
			if (char.class == type || Character.class == type) {
				char[] operand = getCharOperand(args);
				return createCharIntervalOpenComparator(operand[0], operand[1]);
			}
			if (short.class == type || Short.class == type) {
				short[] operand = getShortOperand(args);
				return createShortIntervalOpenComparator(operand[0], operand[1]);
			}
			if (int.class == type || Integer.class == type) {
				int[] operand = getIntegerOperand(args);
				return createIntegerIntervalOpenComparator(operand[0], operand[1]);
			}
			if (long.class == type || Long.class == type) {
				long[] operand = getLongOperand(args);
				return createLongIntervalOpenComparator(operand[0], operand[1]);
			}
			if (float.class == type || Float.class == type) {
				float[] operand = getFloatOperand(args);
				return createFloatIntervalOpenComparator(operand[0], operand[1]);
			}
			if (double.class == type || Double.class == type) {
				double[] operand = getDoubleOperand(args);
				return createDoubleIntervalOpenComparator(operand[0], operand[1]);
			}
			break;

		case INTERVAL_CLOSED:
			if (byte.class == type || Byte.class == type) {
				byte[] operand = getByteOperand(args);
				return createByteIntervalCloseComparator(operand[0], operand[1]);
			}
			if (char.class == type || Character.class == type) {
				char[] operand = getCharOperand(args);
				return createCharIntervalCloseComparator(operand[0], operand[1]);
			}
			if (short.class == type || Short.class == type) {
				short[] operand = getShortOperand(args);
				return createShortIntervalCloseComparator(operand[0], operand[1]);
			}
			if (int.class == type || Integer.class == type) {
				int[] operand = getIntegerOperand(args);
				return createIntegerIntervalCloseComparator(operand[0], operand[1]);
			}
			if (long.class == type || Long.class == type) {
				long[] operand = getLongOperand(args);
				return createLongIntervalCloseComparator(operand[0], operand[1]);
			}
			if (float.class == type || Float.class == type) {
				float[] operand = getFloatOperand(args);
				return createFloatIntervalCloseComparator(operand[0], operand[1]);
			}
			if (double.class == type || Double.class == type) {
				double[] operand = getDoubleOperand(args);
				return createDoubleIntervalCloseComparator(operand[0], operand[1]);
			}
			break;
		case INTERVAL_L_OPEN_R_CLOSED:
			if (byte.class == type || Byte.class == type) {
				byte[] operand = getByteOperand(args);
				return createByteIntervalLOpenRCloseComparator(operand[0], operand[1]);
			}
			if (char.class == type || Character.class == type) {
				char[] operand = getCharOperand(args);
				return createCharIntervalLOpenRCloseComparator(operand[0], operand[1]);
			}
			if (short.class == type || Short.class == type) {
				short[] operand = getShortOperand(args);
				return createShortIntervalLOpenRCloseComparator(operand[0], operand[1]);
			}
			if (int.class == type || Integer.class == type) {
				int[] operand = getIntegerOperand(args);
				return createIntegerIntervalLOpenRCloseComparator(operand[0], operand[1]);
			}
			if (long.class == type || Long.class == type) {
				long[] operand = getLongOperand(args);
				return createLongIntervalLOpenRCloseComparator(operand[0], operand[1]);
			}
			if (float.class == type || Float.class == type) {
				float[] operand = getFloatOperand(args);
				return createFloatIntervalLOpenRCloseComparator(operand[0], operand[1]);
			}
			if (double.class == type || Double.class == type) {
				double[] operand = getDoubleOperand(args);
				return createDoubleIntervalLOpenRCloseComparator(operand[0], operand[1]);
			}
			break;
		case INTERVAL_L_CLOSE_R_OPEN:
			if (byte.class == type || Byte.class == type) {
				byte[] operand = getByteOperand(args);
				return createByteIntervalLCloseROpenComparator(operand[0], operand[1]);
			}
			if (char.class == type || Character.class == type) {
				char[] operand = getCharOperand(args);
				return createCharIntervalLCloseROpenComparator(operand[0], operand[1]);
			}
			if (short.class == type || Short.class == type) {
				short[] operand = getShortOperand(args);
				return createShortIntervalLCloseROpenComparator(operand[0], operand[1]);
			}
			if (int.class == type || Integer.class == type) {
				int[] operand = getIntegerOperand(args);
				return createIntegerIntervalLCloseROpenComparator(operand[0], operand[1]);
			}
			if (long.class == type || Long.class == type) {
				long[] operand = getLongOperand(args);
				return createLongIntervalLCloseROpenComparator(operand[0], operand[1]);
			}
			if (float.class == type || Float.class == type) {
				float[] operand = getFloatOperand(args);
				return createFloatIntervalLCloseROpenComparator(operand[0], operand[1]);
			}
			if (double.class == type || Double.class == type) {
				double[] operand = getDoubleOperand(args);
				return createDoubleIntervalLCloseROpenComparator(operand[0], operand[1]);
			}
			break;
		default:
		}
		throw new UnsupportedOperationException(
				"Unsupported Operator [" + operator + "] or data type[" + type.getName() + "]!");
	}

	// -------------------------------- char ----------------------------------
	private static NumericComparator createByteGTComparator(byte operand) {
		return new NumericComparator() {
			@Override
			public boolean check(Object arg) {
				return (byte) arg > operand;
			}

			@Override
			public Object getOperand1() {
				return operand;
			}

			@Override
			public Object getOperand2() {
				return null;
			}
		};
	}

	private static NumericComparator createByteGEComparator(byte operand) {
		return new NumericComparator() {
			@Override
			public boolean check(Object arg) {
				return (byte) arg >= operand;
			}

			@Override
			public Object getOperand1() {
				return operand;
			}

			@Override
			public Object getOperand2() {
				return null;
			}
		};
	}

	private static NumericComparator createByteLTComparator(byte operand) {
		return new NumericComparator() {
			@Override
			public boolean check(Object arg) {
				return (byte) arg < operand;
			}

			@Override
			public Object getOperand1() {
				return operand;
			}

			@Override
			public Object getOperand2() {
				return null;
			}
		};
	}

	private static NumericComparator createByteLEComparator(byte operand) {
		return new NumericComparator() {
			@Override
			public boolean check(Object arg) {
				return (byte) arg <= operand;
			}

			@Override
			public Object getOperand1() {
				return operand;
			}

			@Override
			public Object getOperand2() {
				return null;
			}
		};
	}

	private static NumericComparator createByteEQComparator(byte operand) {
		return new NumericComparator() {
			@Override
			public boolean check(Object arg) {
				return (byte) arg == operand;
			}

			@Override
			public Object getOperand1() {
				return operand;
			}

			@Override
			public Object getOperand2() {
				return null;
			}
		};
	}

	private static NumericComparator createByteNEComparator(byte operand) {
		return new NumericComparator() {
			@Override
			public boolean check(Object arg) {
				return (byte) arg != operand;
			}

			@Override
			public Object getOperand1() {
				return operand;
			}

			@Override
			public Object getOperand2() {
				return null;
			}
		};
	}

	private static NumericComparator createByteIntervalOpenComparator(byte operand1, byte operand2) {
		return new NumericComparator() {
			@Override
			public boolean check(Object arg) {
				byte value = (byte) arg;
				return operand1 < value && value < operand2;
			}

			@Override
			public Object getOperand1() {
				return operand1;
			}

			@Override
			public Object getOperand2() {
				return operand2;
			}
		};
	}

	private static NumericComparator createByteIntervalCloseComparator(byte operand1, byte operand2) {
		return new NumericComparator() {
			@Override
			public boolean check(Object arg) {
				byte value = (byte) arg;
				return operand1 <= value && value <= operand2;
			}

			@Override
			public Object getOperand1() {
				return operand1;
			}

			@Override
			public Object getOperand2() {
				return operand2;
			}
		};
	}

	private static NumericComparator createByteIntervalLOpenRCloseComparator(byte operand1, byte operand2) {
		return new NumericComparator() {
			@Override
			public boolean check(Object arg) {
				byte value = (byte) arg;
				return operand1 < value && value <= operand2;
			}

			@Override
			public Object getOperand1() {
				return operand1;
			}

			@Override
			public Object getOperand2() {
				return operand2;
			}
		};
	}

	private static NumericComparator createByteIntervalLCloseROpenComparator(byte operand1, byte operand2) {
		return new NumericComparator() {
			@Override
			public boolean check(Object arg) {
				byte value = (byte) arg;
				return operand1 <= value && value < operand2;
			}

			@Override
			public Object getOperand1() {
				return operand1;
			}

			@Override
			public Object getOperand2() {
				return operand2;
			}
		};
	}

	// -------------------------------- char ----------------------------------
	private static NumericComparator createCharGTComparator(char operand) {
		return new NumericComparator() {
			@Override
			public boolean check(Object arg) {
				return (char) arg > operand;
			}

			@Override
			public Object getOperand1() {
				return operand;
			}

			@Override
			public Object getOperand2() {
				return null;
			}
		};
	}

	private static NumericComparator createCharGEComparator(char operand) {
		return new NumericComparator() {
			@Override
			public boolean check(Object arg) {
				return (char) arg >= operand;
			}

			@Override
			public Object getOperand1() {
				return operand;
			}

			@Override
			public Object getOperand2() {
				return null;
			}
		};
	}

	private static NumericComparator createCharLTComparator(char operand) {
		return new NumericComparator() {
			@Override
			public boolean check(Object arg) {
				return (char) arg < operand;
			}

			@Override
			public Object getOperand1() {
				return operand;
			}

			@Override
			public Object getOperand2() {
				return null;
			}
		};
	}

	private static NumericComparator createCharLEComparator(char operand) {
		return new NumericComparator() {
			@Override
			public boolean check(Object arg) {
				return (char) arg <= operand;
			}

			@Override
			public Object getOperand1() {
				return operand;
			}

			@Override
			public Object getOperand2() {
				return null;
			}
		};
	}

	private static NumericComparator createCharEQComparator(char operand) {
		return new NumericComparator() {
			@Override
			public boolean check(Object arg) {
				return (char) arg == operand;
			}

			@Override
			public Object getOperand1() {
				return operand;
			}

			@Override
			public Object getOperand2() {
				return null;
			}
		};
	}

	private static NumericComparator createCharNEComparator(char operand) {
		return new NumericComparator() {
			@Override
			public boolean check(Object arg) {
				return (char) arg != operand;
			}

			@Override
			public Object getOperand1() {
				return operand;
			}

			@Override
			public Object getOperand2() {
				return null;
			}
		};
	}

	private static NumericComparator createCharIntervalOpenComparator(char operand1, char operand2) {
		return new NumericComparator() {
			@Override
			public boolean check(Object arg) {
				char value = (char) arg;
				return operand1 < value && value < operand2;
			}

			@Override
			public Object getOperand1() {
				return operand1;
			}

			@Override
			public Object getOperand2() {
				return operand2;
			}
		};
	}

	private static NumericComparator createCharIntervalCloseComparator(char operand1, char operand2) {
		return new NumericComparator() {
			@Override
			public boolean check(Object arg) {
				char value = (char) arg;
				return operand1 <= value && value <= operand2;
			}

			@Override
			public Object getOperand1() {
				return operand1;
			}

			@Override
			public Object getOperand2() {
				return operand2;
			}
		};
	}

	private static NumericComparator createCharIntervalLOpenRCloseComparator(char operand1, char operand2) {
		return new NumericComparator() {
			@Override
			public boolean check(Object arg) {
				char value = (char) arg;
				return operand1 < value && value <= operand2;
			}

			@Override
			public Object getOperand1() {
				return operand1;
			}

			@Override
			public Object getOperand2() {
				return operand2;
			}
		};
	}

	private static NumericComparator createCharIntervalLCloseROpenComparator(char operand1, char operand2) {
		return new NumericComparator() {
			@Override
			public boolean check(Object arg) {
				char value = (char) arg;
				return operand1 <= value && value < operand2;
			}

			@Override
			public Object getOperand1() {
				return operand1;
			}

			@Override
			public Object getOperand2() {
				return operand2;
			}
		};
	}

	// -------------------------------- short ----------------------------------
	private static NumericComparator createShortGTComparator(short operand) {
		return new NumericComparator() {
			@Override
			public boolean check(Object arg) {
				return (short) arg > operand;
			}

			@Override
			public Object getOperand1() {
				return operand;
			}

			@Override
			public Object getOperand2() {
				return null;
			}
		};
	}

	private static NumericComparator createShortGEComparator(short operand) {
		return new NumericComparator() {
			@Override
			public boolean check(Object arg) {
				return (short) arg >= operand;
			}

			@Override
			public Object getOperand1() {
				return operand;
			}

			@Override
			public Object getOperand2() {
				return null;
			}
		};
	}

	private static NumericComparator createShortLTComparator(short operand) {
		return new NumericComparator() {
			@Override
			public boolean check(Object arg) {
				return (short) arg < operand;
			}

			@Override
			public Object getOperand1() {
				return operand;
			}

			@Override
			public Object getOperand2() {
				return null;
			}
		};
	}

	private static NumericComparator createShortLEComparator(short operand) {
		return new NumericComparator() {
			@Override
			public boolean check(Object arg) {
				return (short) arg <= operand;
			}

			@Override
			public Object getOperand1() {
				return operand;
			}

			@Override
			public Object getOperand2() {
				return null;
			}
		};
	}

	private static NumericComparator createShortEQComparator(short operand) {
		return new NumericComparator() {
			@Override
			public boolean check(Object arg) {
				return (short) arg == operand;
			}

			@Override
			public Object getOperand1() {
				return operand;
			}

			@Override
			public Object getOperand2() {
				return null;
			}
		};
	}

	private static NumericComparator createShortNEComparator(short operand) {
		return new NumericComparator() {
			@Override
			public boolean check(Object arg) {
				return (short) arg != operand;
			}

			@Override
			public Object getOperand1() {
				return operand;
			}

			@Override
			public Object getOperand2() {
				return null;
			}
		};
	}

	private static NumericComparator createShortIntervalOpenComparator(short operand1, short operand2) {
		return new NumericComparator() {
			@Override
			public boolean check(Object arg) {
				short value = (short) arg;
				return operand1 < value && value < operand2;
			}

			@Override
			public Object getOperand1() {
				return operand1;
			}

			@Override
			public Object getOperand2() {
				return operand2;
			}
		};
	}

	private static NumericComparator createShortIntervalCloseComparator(short operand1, short operand2) {
		return new NumericComparator() {
			@Override
			public boolean check(Object arg) {
				short value = (short) arg;
				return operand1 <= value && value <= operand2;
			}

			@Override
			public Object getOperand1() {
				return operand1;
			}

			@Override
			public Object getOperand2() {
				return operand2;
			}
		};
	}

	private static NumericComparator createShortIntervalLOpenRCloseComparator(short operand1, short operand2) {
		return new NumericComparator() {
			@Override
			public boolean check(Object arg) {
				short value = (short) arg;
				return operand1 < value && value <= operand2;
			}

			@Override
			public Object getOperand1() {
				return operand1;
			}

			@Override
			public Object getOperand2() {
				return operand2;
			}
		};
	}

	private static NumericComparator createShortIntervalLCloseROpenComparator(short operand1, short operand2) {
		return new NumericComparator() {
			@Override
			public boolean check(Object arg) {
				short value = (short) arg;
				return operand1 <= value && value < operand2;
			}

			@Override
			public Object getOperand1() {
				return operand1;
			}

			@Override
			public Object getOperand2() {
				return operand2;
			}
		};
	}

	// -------------------------------- int ----------------------------------
	private static NumericComparator createIntegerGTComparator(int operand) {
		return new NumericComparator() {
			@Override
			public boolean check(Object arg) {
				return (int) arg > operand;
			}

			@Override
			public Object getOperand1() {
				return operand;
			}

			@Override
			public Object getOperand2() {
				return null;
			}
		};
	}

	private static NumericComparator createIntegerGEComparator(int operand) {
		return new NumericComparator() {
			@Override
			public boolean check(Object arg) {
				return (int) arg >= operand;
			}

			@Override
			public Object getOperand1() {
				return operand;
			}

			@Override
			public Object getOperand2() {
				return null;
			}
		};
	}

	private static NumericComparator createIntegerLTComparator(int operand) {
		return new NumericComparator() {
			@Override
			public boolean check(Object arg) {
				return (int) arg < operand;
			}

			@Override
			public Object getOperand1() {
				return operand;
			}

			@Override
			public Object getOperand2() {
				return null;
			}
		};
	}

	private static NumericComparator createIntegerLEComparator(int operand) {
		return new NumericComparator() {
			@Override
			public boolean check(Object arg) {
				return (int) arg <= operand;
			}

			@Override
			public Object getOperand1() {
				return operand;
			}

			@Override
			public Object getOperand2() {
				return null;
			}
		};
	}

	private static NumericComparator createIntegerEQComparator(int operand) {
		return new NumericComparator() {
			@Override
			public boolean check(Object arg) {
				return (int) arg == operand;
			}

			@Override
			public Object getOperand1() {
				return operand;
			}

			@Override
			public Object getOperand2() {
				return null;
			}
		};
	}

	private static NumericComparator createIntegerNEComparator(int operand) {
		return new NumericComparator() {
			@Override
			public boolean check(Object arg) {
				return (int) arg != operand;
			}

			@Override
			public Object getOperand1() {
				return operand;
			}

			@Override
			public Object getOperand2() {
				return null;
			}
		};
	}

	private static NumericComparator createIntegerIntervalOpenComparator(int operand1, int operand2) {
		return new NumericComparator() {
			@Override
			public boolean check(Object arg) {
				int value = (int) arg;
				return operand1 < value && value < operand2;
			}

			@Override
			public Object getOperand1() {
				return operand1;
			}

			@Override
			public Object getOperand2() {
				return operand2;
			}
		};
	}

	private static NumericComparator createIntegerIntervalCloseComparator(int operand1, int operand2) {
		return new NumericComparator() {
			@Override
			public boolean check(Object arg) {
				int value = (int) arg;
				return operand1 <= value && value <= operand2;
			}

			@Override
			public Object getOperand1() {
				return operand1;
			}

			@Override
			public Object getOperand2() {
				return operand2;
			}
		};
	}

	private static NumericComparator createIntegerIntervalLOpenRCloseComparator(int operand1, int operand2) {
		return new NumericComparator() {
			@Override
			public boolean check(Object arg) {
				int value = (int) arg;
				return operand1 < value && value <= operand2;
			}

			@Override
			public Object getOperand1() {
				return operand1;
			}

			@Override
			public Object getOperand2() {
				return operand2;
			}
		};
	}

	private static NumericComparator createIntegerIntervalLCloseROpenComparator(int operand1, int operand2) {
		return new NumericComparator() {
			@Override
			public boolean check(Object arg) {
				int value = (int) arg;
				return operand1 <= value && value < operand2;
			}

			@Override
			public Object getOperand1() {
				return operand1;
			}

			@Override
			public Object getOperand2() {
				return operand2;
			}
		};
	}

	// -------------------------------- long ----------------------------------
	private static NumericComparator createLongGTComparator(long operand) {
		return new NumericComparator() {
			@Override
			public boolean check(Object arg) {
				return (long) arg > operand;
			}

			@Override
			public Object getOperand1() {
				return operand;
			}

			@Override
			public Object getOperand2() {
				return null;
			}
		};
	}

	private static NumericComparator createLongGEComparator(long operand) {
		return new NumericComparator() {
			@Override
			public boolean check(Object arg) {
				return (long) arg >= operand;
			}

			@Override
			public Object getOperand1() {
				return operand;
			}

			@Override
			public Object getOperand2() {
				return null;
			}
		};
	}

	private static NumericComparator createLongLTComparator(long operand) {
		return new NumericComparator() {
			@Override
			public boolean check(Object arg) {
				return (long) arg < operand;
			}

			@Override
			public Object getOperand1() {
				return operand;
			}

			@Override
			public Object getOperand2() {
				return null;
			}
		};
	}

	private static NumericComparator createLongLEComparator(long operand) {
		return new NumericComparator() {
			@Override
			public boolean check(Object arg) {
				return (long) arg <= operand;
			}

			@Override
			public Object getOperand1() {
				return operand;
			}

			@Override
			public Object getOperand2() {
				return null;
			}
		};
	}

	private static NumericComparator createLongEQComparator(long operand) {
		return new NumericComparator() {
			@Override
			public boolean check(Object arg) {
				return (long) arg == operand;
			}

			@Override
			public Object getOperand1() {
				return operand;
			}

			@Override
			public Object getOperand2() {
				return null;
			}
		};
	}

	private static NumericComparator createLongNEComparator(long operand) {
		return new NumericComparator() {
			@Override
			public boolean check(Object arg) {
				return (long) arg != operand;
			}

			@Override
			public Object getOperand1() {
				return operand;
			}

			@Override
			public Object getOperand2() {
				return null;
			}
		};
	}

	private static NumericComparator createLongIntervalOpenComparator(long operand1, long operand2) {
		return new NumericComparator() {
			@Override
			public boolean check(Object arg) {
				long value = (long) arg;
				return operand1 < value && value < operand2;
			}

			@Override
			public Object getOperand1() {
				return operand1;
			}

			@Override
			public Object getOperand2() {
				return operand2;
			}
		};
	}

	private static NumericComparator createLongIntervalCloseComparator(long operand1, long operand2) {
		return new NumericComparator() {
			@Override
			public boolean check(Object arg) {
				long value = (long) arg;
				return operand1 <= value && value <= operand2;
			}

			@Override
			public Object getOperand1() {
				return operand1;
			}

			@Override
			public Object getOperand2() {
				return operand2;
			}
		};
	}

	private static NumericComparator createLongIntervalLOpenRCloseComparator(long operand1, long operand2) {
		return new NumericComparator() {
			@Override
			public boolean check(Object arg) {
				long value = (long) arg;
				return operand1 < value && value <= operand2;
			}

			@Override
			public Object getOperand1() {
				return operand1;
			}

			@Override
			public Object getOperand2() {
				return operand2;
			}
		};
	}

	private static NumericComparator createLongIntervalLCloseROpenComparator(long operand1, long operand2) {
		return new NumericComparator() {
			@Override
			public boolean check(Object arg) {
				long value = (long) arg;
				return operand1 <= value && value < operand2;
			}

			@Override
			public Object getOperand1() {
				return operand1;
			}

			@Override
			public Object getOperand2() {
				return operand2;
			}
		};
	}

	// -------------------------------- float ----------------------------------
	private static NumericComparator createFloatGTComparator(float operand) {
		return new NumericComparator() {
			@Override
			public boolean check(Object arg) {
				return (float) arg > operand;
			}

			@Override
			public Object getOperand1() {
				return operand;
			}

			@Override
			public Object getOperand2() {
				return null;
			}
		};
	}

	private static NumericComparator createFloatGEComparator(float operand) {
		return new NumericComparator() {
			@Override
			public boolean check(Object arg) {
				return (float) arg >= operand;
			}

			@Override
			public Object getOperand1() {
				return operand;
			}

			@Override
			public Object getOperand2() {
				return null;
			}
		};
	}

	private static NumericComparator createFloatLTComparator(float operand) {
		return new NumericComparator() {
			@Override
			public boolean check(Object arg) {
				return (float) arg < operand;
			}

			@Override
			public Object getOperand1() {
				return operand;
			}

			@Override
			public Object getOperand2() {
				return null;
			}
		};
	}

	private static NumericComparator createFloatLEComparator(float operand) {
		return new NumericComparator() {
			@Override
			public boolean check(Object arg) {
				return (float) arg <= operand;
			}

			@Override
			public Object getOperand1() {
				return operand;
			}

			@Override
			public Object getOperand2() {
				return null;
			}
		};
	}

	private static NumericComparator createFloatEQComparator(float operand) {
		return new NumericComparator() {
			@Override
			public boolean check(Object arg) {
				return (float) arg == operand;
			}

			@Override
			public Object getOperand1() {
				return operand;
			}

			@Override
			public Object getOperand2() {
				return null;
			}
		};
	}

	private static NumericComparator createFloatNEComparator(float operand) {
		return new NumericComparator() {
			@Override
			public boolean check(Object arg) {
				return (float) arg != operand;
			}

			@Override
			public Object getOperand1() {
				return operand;
			}

			@Override
			public Object getOperand2() {
				return null;
			}
		};
	}

	private static NumericComparator createFloatIntervalOpenComparator(float operand1, float operand2) {
		return new NumericComparator() {
			@Override
			public boolean check(Object arg) {
				float value = (float) arg;
				return operand1 < value && value < operand2;
			}

			@Override
			public Object getOperand1() {
				return operand1;
			}

			@Override
			public Object getOperand2() {
				return operand2;
			}
		};
	}

	private static NumericComparator createFloatIntervalCloseComparator(float operand1, float operand2) {
		return new NumericComparator() {
			@Override
			public boolean check(Object arg) {
				float value = (float) arg;
				return operand1 <= value && value <= operand2;
			}

			@Override
			public Object getOperand1() {
				return operand1;
			}

			@Override
			public Object getOperand2() {
				return operand2;
			}
		};
	}

	private static NumericComparator createFloatIntervalLOpenRCloseComparator(float operand1, float operand2) {
		return new NumericComparator() {
			@Override
			public boolean check(Object arg) {
				float value = (float) arg;
				return operand1 < value && value <= operand2;
			}

			@Override
			public Object getOperand1() {
				return operand1;
			}

			@Override
			public Object getOperand2() {
				return operand2;
			}
		};
	}

	private static NumericComparator createFloatIntervalLCloseROpenComparator(float operand1, float operand2) {
		return new NumericComparator() {
			@Override
			public boolean check(Object arg) {
				float value = (float) arg;
				return operand1 <= value && value < operand2;
			}

			@Override
			public Object getOperand1() {
				return operand1;
			}

			@Override
			public Object getOperand2() {
				return operand2;
			}
		};
	}

	// -------------------------------- double
	// ----------------------------------
	private static NumericComparator createDoubleGTComparator(double operand) {
		return new NumericComparator() {
			@Override
			public boolean check(Object arg) {
				return (double) arg > operand;
			}

			@Override
			public Object getOperand1() {
				return operand;
			}

			@Override
			public Object getOperand2() {
				return null;
			}
		};
	}

	private static NumericComparator createDoubleGEComparator(double operand) {
		return new NumericComparator() {
			@Override
			public boolean check(Object arg) {
				return (double) arg >= operand;
			}

			@Override
			public Object getOperand1() {
				return operand;
			}

			@Override
			public Object getOperand2() {
				return null;
			}
		};
	}

	private static NumericComparator createDoubleLTComparator(double operand) {
		return new NumericComparator() {
			@Override
			public boolean check(Object arg) {
				return (double) arg < operand;
			}

			@Override
			public Object getOperand1() {
				return operand;
			}

			@Override
			public Object getOperand2() {
				return null;
			}
		};
	}

	private static NumericComparator createDoubleLEComparator(double operand) {
		return new NumericComparator() {
			@Override
			public boolean check(Object arg) {
				return (double) arg <= operand;
			}

			@Override
			public Object getOperand1() {
				return operand;
			}

			@Override
			public Object getOperand2() {
				return null;
			}
		};
	}

	private static NumericComparator createDoubleEQComparator(double operand) {
		return new NumericComparator() {
			@Override
			public boolean check(Object arg) {
				return (double) arg == operand;
			}

			@Override
			public Object getOperand1() {
				return operand;
			}

			@Override
			public Object getOperand2() {
				return null;
			}
		};
	}

	private static NumericComparator createDoubleNEComparator(double operand) {
		return new NumericComparator() {
			@Override
			public boolean check(Object arg) {
				return (double) arg != operand;
			}

			@Override
			public Object getOperand1() {
				return operand;
			}

			@Override
			public Object getOperand2() {
				return null;
			}
		};
	}

	private static NumericComparator createDoubleIntervalOpenComparator(double operand1, double operand2) {
		return new NumericComparator() {
			@Override
			public boolean check(Object arg) {
				double value = (double) arg;
				return operand1 < value && value < operand2;
			}

			@Override
			public Object getOperand1() {
				return operand1;
			}

			@Override
			public Object getOperand2() {
				return operand2;
			}
		};
	}

	private static NumericComparator createDoubleIntervalCloseComparator(double operand1, double operand2) {
		return new NumericComparator() {
			@Override
			public boolean check(Object arg) {
				double value = (double) arg;
				return operand1 <= value && value <= operand2;
			}

			@Override
			public Object getOperand1() {
				return operand1;
			}

			@Override
			public Object getOperand2() {
				return operand2;
			}
		};
	}

	private static NumericComparator createDoubleIntervalLOpenRCloseComparator(double operand1, double operand2) {
		return new NumericComparator() {
			@Override
			public boolean check(Object arg) {
				double value = (double) arg;
				return operand1 < value && value <= operand2;
			}

			@Override
			public Object getOperand1() {
				return operand1;
			}

			@Override
			public Object getOperand2() {
				return operand2;
			}
		};
	}

	private static NumericComparator createDoubleIntervalLCloseROpenComparator(double operand1, double operand2) {
		return new NumericComparator() {
			@Override
			public boolean check(Object arg) {
				double value = (double) arg;
				return operand1 <= value && value < operand2;
			}

			@Override
			public Object getOperand1() {
				return operand1;
			}

			@Override
			public Object getOperand2() {
				return operand2;
			}
		};
	}
}
