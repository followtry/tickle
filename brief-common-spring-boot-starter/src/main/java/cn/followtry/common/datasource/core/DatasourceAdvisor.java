package cn.followtry.common.datasource.core;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractGenericPointcutAdvisor;

/**
 * @author followtry
 * @since 2021/7/29 11:39 上午
 */
public class DatasourceAdvisor extends AbstractGenericPointcutAdvisor {


    private Pointcut pointcut;

    public static final int ORDER_CODE = 1;

    @Override
    public Pointcut getPointcut() {
        return pointcut;
    }

    public DatasourceAdvisor(Pointcut pointcut,Advice advice) {
        super();
        super.setOrder(ORDER_CODE);
        super.setAdvice(advice);
        this.pointcut = pointcut;

    }


}
