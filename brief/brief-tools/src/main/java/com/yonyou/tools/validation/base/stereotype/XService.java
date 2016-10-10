/**
 * 
 */
package com.yonyou.tools.validation.base.stereotype;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.core.annotation.AliasFor;

/**
 * @author jingzz
 * @time 2016年8月25日 下午6:57:15
 * @name yycollege/com.yonyou.worktime.base.stereotype.validation.ServiceAop
 * @since 2016年8月25日 下午6:57:15
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface XService {
	
	@AliasFor("value")
	String name() default "";
	
	@AliasFor("name")
	String value() default "";
}
