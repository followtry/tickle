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
import org.springframework.stereotype.Repository;

/**
 * 处理controller的aop
 * @author jingzz
 * @time 2016年8月25日 下午7:23:37
 * @name yycollege/com.yonyou.worktime.base.stereotype.validation.ControllerAop
 * @since 2016年8月25日 下午7:23:37
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Repository
public @interface XRepository {
	
	@AliasFor("value")
	String name() default "";
	
	@AliasFor("name")
	String value() default "";

}
