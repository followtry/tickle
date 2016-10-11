package com.yonyou.tools.validation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * NotNull 非空约束；
 * 
 * 非空约束的优先级是最高的，高于其它类型的约束；
 * 
 * 不能对同一个目标应用超过 1 个 NotNull 标注；
 * 
 * @author haiq
 *
 */
@Constraint
@Target({ ElementType.METHOD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface NotNull{
	
	/**
	 * 目标对象的名称；
	 * 
	 * 用于在校验失败时输出错误信息；
	 * 
	 * @return
	 */
	String name() default("");
	
}
