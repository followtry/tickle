package cn.followtry.java8.repeatable;

/**
 * Created by jingzhongzhi on 2017/9/17.
 */

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注解的数组声明，用于重复注解
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE,ElementType.FIELD,ElementType.PARAMETER,ElementType.METHOD})
public @interface AnnoTypeArr {
    
    /**
     * 注解集合
     * @return
     */
    AnnoType1[] value() ;
}
