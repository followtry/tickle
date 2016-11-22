package cn.followtry.validation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import cn.followtry.validation.base.stereotype.validation.CustomConstraintHandle;

/**
 * CustomCheck 自定义约束；
 * 
 * 可以对任意类型的参数、Java Bean属性的 get方法进行标注；
 * 
 * 注：针对非 Java Bean 属性的 get 方法或者服务接口的方式上进行的标注不会得到处理；
 * 
 * 同一个目标上只能标注一个自定义约束检查；
 * 
 * 如果目标上已经标注了其它的非自定义约束，标注的自定义约束检查将被加入到检查列表的末尾；
 * 
 * @author haiq
 *
 */
@Constraint
@Target({ ElementType.METHOD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomCheck {
	
	/**
	 * 目标对象的名称；
	 * 
	 * 用于在校验失败时输出错误信息；
	 * 
	 * @return 目标对象的名称；
	 */
	String name() default("");

	/**
	 * 自定义的约束校验器的实现类；
	 * 
	 * 指定的类型必须实现 {@link CustomConstraintHandle} 接口；
	 * 
	 * @return  自定义的约束校验器的实现类
	 */
	Class<?>handler();

	/**
	 * 参数；
	 * 
	 * @return  参数列表
	 */
	String[]args();

	/**
	 * 次序；
	 * 
	 * 用于控制同一目标上定义多个自定义约束的校验顺序；
	 * 
	 * 注意，仅仅在多个自定义约束之间排序，而不涉及到其它预定义约束类型的次序；
	 * 
	 * @return 次序
	 */
	int order() default (0);

	/**
	 * 是否忽略空值；
	 * 
	 * 如果设为 true，则当标注的属性值为空时不做约束校验；
	 * 
	 * 如果设为 false，则当标注的属性值为空时将引发空值校验失败错误，此时等同于 NotNull 约束；
	 * 
	 * @return 是否忽略空值
	 */
	boolean nullable() default (true);
}
