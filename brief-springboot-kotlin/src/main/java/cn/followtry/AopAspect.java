package cn.followtry;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author followtry
 * @since 2021/7/27 4:47 下午
 */
@Aspect
@Component
public class AopAspect {

    @Pointcut(value = "execution( * cn.followtry.*.*(..) )")
    public void pointCut(){
    }

    @Around(value = "pointCut()")
    public Object aroundProcess(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("代码前执行");
        Object proceed = joinPoint.proceed();
        System.out.println("代码后执行");
        return proceed;
    }
}
