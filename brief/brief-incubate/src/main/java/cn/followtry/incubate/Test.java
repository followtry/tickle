package cn.followtry.incubate;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *  brief-incubate/cn.followtry.incubate.Test
 * @author 
 *		jingzz 
 * @since 
 *		2017年1月12日 下午1:22:09
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Test {

}
