package cn.followtry.validation.base;


import org.aspectj.lang.ProceedingJoinPoint;

public interface AspectServiceChain {


	void doCheck(ProceedingJoinPoint joinPoint);
}
