package com.yonyou.tools.validation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 对集合非空校验
 * @author jingzz
 * @time 2016年3月18日 下午2:49:22
 * @name base-context/com.yonyou.worktime.base.stereotype.validation.CollectionCheck
 * @since 2016年3月18日 下午2:49:22
 */
@Constraint
@Target({ ElementType.METHOD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface CollectionCheck{
	
	/**
	 * 目标对象的名称；
	 * 
	 * 用于在校验失败时输出错误信息；
	 * 
	 * @return
	 */
	String name();
	
	/**
	 * 对集合的非空校验值，默认不校验
	 * true:校验集合是否为空或null，false：不校验集合
	 * @author jingzz
	 * @return
	 */
	boolean value() default(false);
	
}
