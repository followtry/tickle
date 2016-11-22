package cn.followtry.validation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import cn.followtry.validation.base.stereotype.validation.StringOperator;

/**
 * StringCheck 字符约束；
 * 
 * 针对字符类型或者能够转换为字符串的类型的参数、Java Bean属性的 get方法进行标注；
 * 
 * 字符类型包括 CharSequence、String、StringBuilder、StringBuffer；
 * 
 * @author haiq
 *
 */
@Constraint
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(value = StringChecks.class)
public @interface StringCheck {
	
	public static final int[] EMPTY_LENTHS = {};
	
	/**
	 * 目标对象的名称；
	 * 
	 * 用于在校验失败时输出错误信息；
	 * 
	 * @return 目标对象的名称
	 */
	String name() default("");
	
	/**
	 * @return 文本操作符
	 */
	StringOperator value();

	/**
	 * 长度参数；
	 * 
	 * 用于长度方面的约束校验；
	 * 
	 * @return 长度参数
	 */
	int[] lengthArgs() default(-1);
	
	/**
	 * 文本参数；
	 * 
	 * 用于文本方面的约束校验，包括“开始于(START_WITH)”, “结束于(END_WITH)”, “包含(CONATIN)”, “正则表达式(REGEX)”
	 * 
	 * @return 文本参数
	 */
	String textArg()default("");
	
	/**
	 * 正则表达式别名；
	 * 
	 * 用于按正则表达式约束校验失败时描述正则表达式描述的格式名称；
	 * 
	 * @return 正则表达式别名
	 */
	String regexAlias() default("");

	/**
	 * 是否自动截取字符串的两头的空白字符；
	 * 
	 * 默认为 false；
	 * 
	 * 设置为 true 时将自动截取声明的原始字段数据的两头的空白字符之后再校验，
	 * 
	 * 注意：截取空白字符之后的结果仅作为校验的输入，但不会影响标注目标的原值；
	 * 
	 * 当选择 StringOperator.NOT_EMPTY 时，忽略此设置而总是执行 autoTrim 操作；
	 * 
	 * @return  是否自动截取字符串的两头的空白字符
	 */
	boolean autoTrim() default (false);
	
	/**
	 * 是否以标注目标的 toString 方法返回的文本值作为输入值进行校验；
	 * 
	 * 此选项通常用于标注的目标类型不是字符类型的情形；
	 * 
	 * 该选项的默认值为 false；
	 * 
	 * 注意：当将给选项的值设为 false 时，不能用于标注非字符类型的目标；
	 * 
	 * @return 是否以标注目标的 toString 方法返回的文本值作为输入值进行校验
	 */
	boolean checkToString() default (false);
	
	
	/**
	 * 是否忽略空值；
	 * 
	 * 如果设为 true，则当标注的属性值为空时不做约束校验；
	 * 
	 * 如果设为 false，则当标注的属性值为空时将引发空值校验失败错误，此时等同于 NOT_NULL 约束；
	 * 
	 * 当选择 StringOperator.NOT_EMPTY 时，忽略此设置而总是执行 NOT_NULL 操作；
	 * 
	 * @return 是否忽略空值
	 */
	boolean nullable() default(true);
}
