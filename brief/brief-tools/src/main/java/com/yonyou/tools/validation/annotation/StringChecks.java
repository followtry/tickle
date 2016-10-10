package com.yonyou.tools.validation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

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
public @interface StringChecks {
	
	StringCheck[] value();
}
