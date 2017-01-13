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
@XComponent
public @interface XRepository {
	
	@AliasFor("value")
	String name() default "";
	
	@AliasFor("name")
	String value() default "";
	
	/**
	 * 默认检查所有的日志
	 * @author jingzz
	 * @return
	 */
	boolean allMethods() default true;
	
	/**
	 * 设置要监控的方法集合，默认为空。如果{@link setAllMethods}方法设置为true，则该设置不起作用。
	 * @author jingzz
	 * @return
	 */
	String[] monitorMethods() default "";

}
