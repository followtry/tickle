package cn.followtry.java8.repeatable;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 重复注解的基本元注解
 * Created by jingzhongzhi on 2017/9/17.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD,ElementType.PARAMETER,ElementType.FIELD})
@Repeatable(value = AnnoTypeArr.class)
public @interface AnnoType1 {
    String value();
}
