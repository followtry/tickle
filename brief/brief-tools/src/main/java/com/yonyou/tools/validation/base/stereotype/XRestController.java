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
import org.springframework.web.bind.annotation.RestController;

/**
 * 处理RestController的aop
 * @author jingzz
 * @time 2016年10月11日 上午9:53:35
 * @name brief-tools/com.yonyou.tools.validation.base.stereotype.XRController
 * @since 2016年10月11日 上午9:53:35
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@RestController
public @interface XRestController {
	
	@AliasFor("value")
	String name() default "";
	
	@AliasFor("name")
	String value() default "";

}
