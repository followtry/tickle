package cn.followtry.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author followtry
 * @since 2021/7/29 11:41 上午
 */
public class MyAopAdvice implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        String canonicalName = invocation.getThis().getClass().getCanonicalName();
        String name = invocation.getMethod().getName();
        String fullName = canonicalName + "." + name;
        System.out.println("cn.followtry.aop.MyAopAdvice.invoke()  自定义的aop切面解析. " + fullName);
        return invocation.proceed();
    }
}
