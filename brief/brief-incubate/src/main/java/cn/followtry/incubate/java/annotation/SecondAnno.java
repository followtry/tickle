package cn.followtry.incubate.java.annotation;

import java.lang.annotation.*;

/**
 *  brief-incubate/cn.followtry.incubate.SecondAnno
 * @author 
 *		jingzz 
 * @since 
 *		2017年1月12日 下午1:22:09
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SecondAnno {

	String name() default "";

}
