package cn.followtry.incubate;

import java.lang.annotation.Annotation;

/**
 *  brief-incubate/cn.followtry.incubate.AnnoTest
 * @author 
 *		jingzz 
 * @since 
 *		2017年1月12日 下午1:21:53
 */
@Test2
public class AnnoTest {
	public static void main(String[] args) {
		Annotation[] annotations = AnnoTest.class.getAnnotations();
		for (Annotation annotation : annotations) {
			System.out.println(annotation.toString());
			Class<? extends Annotation> annotationType = annotation.annotationType();
			Annotation[] annotations2 = annotationType.getAnnotations();
			for (Annotation annotation2 : annotations2) {
				System.out.println(annotation2);
			}
		}
	}
}
