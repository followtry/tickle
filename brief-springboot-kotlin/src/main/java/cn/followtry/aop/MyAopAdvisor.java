package cn.followtry.aop;

import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractGenericPointcutAdvisor;

/**
 * @author followtry
 * @since 2021/7/29 11:39 上午
 */
public class MyAopAdvisor extends AbstractGenericPointcutAdvisor {

    
    private static final Pointcut pointcut = new MyAopPointCut();

    @Override
    public Pointcut getPointcut() {
        return pointcut;
    }

    public MyAopAdvisor() {
        super();
    }

    @Override
    public void setAdvice(Advice advice) {
        super.setAdvice(advice);
    }

    @Override
    public Advice getAdvice() {
        return super.getAdvice();
    }

    @Override
    public void setOrder(int order) {
        super.setOrder(order);
    }

    @Override
    public int getOrder() {
        return super.getOrder();
    }

    @Override
    public boolean isPerInstance() {
        return super.isPerInstance();
    }
}
