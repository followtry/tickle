package cn.followtry.common.monitor.core;

import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractGenericPointcutAdvisor;

/**
 * @author followtry
 * @since 2021/7/29 11:39 上午
 */
public class MonitorLogAdvisor extends AbstractGenericPointcutAdvisor {


    private static final Pointcut POINTCUT = new MonitorLogPointCut();

    public static final int ORDER_CODE = 1;

    @Override
    public Pointcut getPointcut() {
        return POINTCUT;
    }

    public MonitorLogAdvisor() {
        super();
        super.setOrder(ORDER_CODE);
    }
}
