package cn.followtry.aop;


import java.lang.annotation.*;

/**
 * 标记注解
 * @author followtry
 * @since 2021/7/29 11:55 上午
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface MyAopLog {
}
