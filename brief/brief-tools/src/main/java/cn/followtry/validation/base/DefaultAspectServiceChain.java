package cn.followtry.validation.base;

import org.aspectj.lang.ProceedingJoinPoint;

import cn.followtry.validation.base.validation.ValidationAspect;

/**
 * 默认的切面服务责任链
 * @author jingzz
 * @since 2016年11月23日 下午3:06:18
 */
public class DefaultAspectServiceChain implements AspectServiceChain{

	private ValidationAspect validation = new ValidationAspect();
	

    @Override
	public void doCheck(ProceedingJoinPoint joinPoint){
		
		validation.check(joinPoint);
		
	}

}
