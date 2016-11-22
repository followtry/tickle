package cn.followtry.validation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import cn.followtry.validation.base.stereotype.validation.ComparisonOperator;

/**
 * NumericCheck 数值约束；
 * 
 * 针对数值类型的参数、Java Bean属性的 get方法进行标注；
 * 
 * 数值类型包括 byte、char、int、long、float、double 以及对应的包装类型；
 * 
 * @author haiq
 *
 */
@Constraint
@Target({ ElementType.METHOD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(value = NumericChecks.class)
public @interface NumericCheck{
	
	
	/**
	 * 目标对象的名称；
	 * 
	 * 用于在校验失败时输出错误信息；
	 * 
	 * @return 名称
	 */
	String name() default("");
	
	/**
	 * 比较运算符；
	 * 
	 * @return 比较运算符
	 */
	 ComparisonOperator value();
	
	/**
	 * 比较参数；
	 * 
	 * @return 参数
	 */
	double[] args();
	
	/**
	 * 是否忽略空值；
	 * 
	 * 如果设为 true，则当标注的属性值为空时不做约束校验；
	 * 
	 * 如果设为 false，则当标注的属性值为空时将引发空值校验失败错误，此时等同于 NotNull 约束；
	 * 
	 * @return 是否忽略空值
	 */
	boolean nullable() default(true);
}
