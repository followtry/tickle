/**
 * 
 */
package cn.jingzz.brief.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.Ordered;

/**
 * @author jingzz
 * @time 2016年6月2日 下午8:38:11
 * @name brief-service/cn.jingzz.brief.aop.MyAop
 * @since 2016年6月2日 下午8:38:11
 */
public class MyAop implements Ordered {

	@Override
	public int getOrder() {
		return 0;
	}
	
	@Around(value = "")
	public void test(){
		
	}
	
	@Pointcut(AspectJointPoints.BUSINESS_SERVICE_ACTIONS)
	public void myPointCut(ProceedingJoinPoint joinPoint) throws Throwable{
		around(joinPoint);
	}

	public Object around(ProceedingJoinPoint joinPoint) throws Throwable{
		long startTs = System.currentTimeMillis();
		System.out.println("before joinPointer:"+startTs);
		Object retn = joinPoint.proceed();
		System.out.println("end joinPointer:"+System.currentTimeMillis());
		return retn;
	}
}
