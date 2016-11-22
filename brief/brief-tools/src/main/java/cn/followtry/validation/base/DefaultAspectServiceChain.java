package cn.followtry.validation.base;

import org.aspectj.lang.ProceedingJoinPoint;

import cn.followtry.validation.base.validation.ValidationAspect;

public class DefaultAspectServiceChain implements AspectServiceChain{

	private ValidationAspect validation = new ValidationAspect();
	
	@Override
	public void doService(ProceedingJoinPoint joinPoint) throws Throwable{
		
		validation.check(joinPoint);
		
	}

}
