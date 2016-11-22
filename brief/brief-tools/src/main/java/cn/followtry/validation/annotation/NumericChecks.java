package cn.followtry.validation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

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
public @interface NumericChecks{
	NumericCheck[] value();
}
