package cn.followtry.incubate.java.annotation;

import java.lang.annotation.*;

/**
 *  brief-incubate/cn.followtry.incubate.FirstAnno
 * @author 
 *		jingzz 
 * @since 
 *		2017年1月12日 下午1:22:42
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@SecondAnno
public @interface FirstAnno {

	String[] value() default "";

}
