/**
 * 
 */
package cn.followtry.validation.base.stereotype;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.core.annotation.AliasFor;

/**
 * 处理RestController的aop
 * @author followtry
 * @since 2016年10月11日 上午9:53:35
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface XRestController {
	
	@AliasFor("value")
	String name() default "";
	
	@AliasFor("name")
	String value() default "";

}
