package com.yonyou.tools.validation.base;

import org.aspectj.lang.ProceedingJoinPoint;

import com.yonyou.tools.validation.base.validation.ValidationAspect;

public class DefaultAspectServiceChain implements AspectServiceChain{

	private ValidationAspect validation = new ValidationAspect();
	
	@Override
	public void doService(ProceedingJoinPoint joinPoint) throws Throwable{
		
		validation.check(joinPoint);
		
	}

}
