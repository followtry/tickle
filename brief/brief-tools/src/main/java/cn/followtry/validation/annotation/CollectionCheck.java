package cn.followtry.validation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 对集合非空校验.
 *
 * @author jingzz
 * @since 2016年3月18日 下午2:49:22 创建时间
 */
@Constraint
@Target({ ElementType.METHOD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface CollectionCheck {

  /**
   * 目标对象的名称.
   * <p>用于在校验失败时输出错误信息；
   *
   * @return 目标对象的名称
   */
  String name();

  /**
   * 对集合的非空校验值，默认不校验 true:校验集合是否为空或null，false：不校验集合
   *
   * @return 判断集合是否为空
   */
  boolean value() default ( false );

}
