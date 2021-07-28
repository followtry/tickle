package cn.followtry;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.weaver.tools.JoinPointMatch;
import org.springframework.stereotype.Component;

/**
 * 切面实现原理参考： <https://blog.csdn.net/supzhili/article/details/98401855/>
 *
 * @author followtry
 * @since 2021/7/27 4:47 下午
 */
@Aspect
@Component
public class AopAspect {

    @Pointcut(value = "execution( * cn.followtry.*.*(..) )")
    public void pointCut() {
    }

    @Around(value = "pointCut()")
    public Object aroundProcess(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("aroundProcess before");
        Object proceed = joinPoint.proceed();
        System.out.println("aroundProcess after=" + proceed);
        return proceed;
    }

    @Before(value = "pointCut()")
    public void beforeProcess() {
        System.out.println("beforeProcess");
    }

    @After(value = "pointCut()")
    public void afterProcess(JoinPoint joinPoint) {
        System.out.println("afterProcess=" + JsonUtils.toJsonString(joinPoint.getArgs()));
    }

    @AfterReturning(value = "pointCut()", returning = "result")
    public void afterReturnProcess(JoinPoint joinPoint, Object result) {
        System.out.println("afterReturnProcess param=" + joinPoint.getArgs()[0] + "; res=" + result);
    }


}
