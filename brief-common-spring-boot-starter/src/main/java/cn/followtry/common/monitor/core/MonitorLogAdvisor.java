package cn.followtry.common.monitor.core;

import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractGenericPointcutAdvisor;

/**
 * @author followtry
 * @since 2021/7/29 11:39 上午
 */
public class MonitorLogAdvisor extends AbstractGenericPointcutAdvisor {


    private static final Pointcut pointcut = new MonitorLogPointCut();

    public static final int ORDER_CODE = 1;

    @Override
    public Pointcut getPointcut() {
        return pointcut;
    }

    public MonitorLogAdvisor() {
        super();
        super.setOrder(ORDER_CODE);
    }
}
