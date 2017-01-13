package cn.followtry.validation.utils;

import java.lang.annotation.Annotation;

/**
 * 注解的工具类
 * @author 
 *		jingzz 
 * @since 
 *		0.0.3
 * @version
 *		0.0.3
 */
public class AnnoUtils {
	
	/**
	 * 通过指定的注解类型，从目标实例中获取对应注解
	 * @author jingzz
	 * @param target 目标实例
	 * @param targetAnno  要获取的注解类型
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Annotation> T getAnno(Object target,Class<T> targetAnno) {
		Annotation annotation = target.getClass().getAnnotation(targetAnno);
		return (T)annotation;
	}
}
