package cn.followtry.boot.java.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author jingzhongzhi
 * @Description
 * @since 2020/1/20
 */
@Component
@Aspect
public class LogAop {

    private static final Logger log = LoggerFactory.getLogger(LogAop.class);

    @Pointcut("execution(public * cn.followtry.boot.java..*.*(..))")
    public void methodPointCut() {

    }

    @Around("methodPointCut()")
    public Object aroundMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        final Method method = MethodSignature.class.cast(joinPoint.getSignature()).getMethod();

        log.info("before method log mark: {}", method.getName());
        Object proceed = joinPoint.proceed();
        log.info("after method log mark: {}", method.getName());
        return proceed;

    }

}
