/**
 * 
 */
package cn.followtry.springboot.springinaction.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 使用@Around注解对指定方法进行切面操作
 * @author jingzz
 * @time 2016年10月12日 上午11:18:16
 * @name brief-example/cn.jingzztech.prac.springinaction.aop.Performance
 * @since 2016年10月12日 上午11:18:16
 */
@Aspect
@Component
public class Audience2 {
	
	private static final Logger LOG = LoggerFactory.getLogger(Audience2.class);
	
	private static final String pointCutPoint ="execution(** cn.followtry.springboot.springinaction.aop.Performance.perform(java.lang.String)) and args(name)";
	
	@Around(pointCutPoint)
	public Object pointCutPoint(ProceedingJoinPoint joinPoint,String name){
		
		Object this1 = joinPoint.getThis();
		
		Object target = joinPoint.getTarget();
		String longString = joinPoint.getSignature().toLongString();
		String longString2 = joinPoint.getStaticPart().toLongString();
		
		System.out.println("this1:"+this1);
		System.out.println("target:"+target);
		System.out.println("longString:"+longString);
		System.out.println("longString2:"+longString2);
		System.out.println("Audience2.pointCutPoint() start name:"+name);
		try {
			Object proceed = joinPoint.proceed();
			System.out.println("Audience2.pointCutPoint() end name:"+name);
			return proceed;
		} catch (Throwable e) {
			LOG.error("切面出错",e);
			throw new RuntimeException("切面出错",e);
		}
		
	}
}
