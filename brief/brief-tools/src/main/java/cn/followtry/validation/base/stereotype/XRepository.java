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
 * 处理仓库层的aop
 * @author foll
 * @since 2016年8月25日 下午7:23:37
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface XRepository {
	
	@AliasFor("value")
	String name() default "";
	
	@AliasFor("name")
	String value() default "";

}