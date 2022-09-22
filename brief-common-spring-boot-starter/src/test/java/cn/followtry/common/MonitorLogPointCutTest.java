package cn.followtry.common;

import cn.followtry.common.monitor.core.MonitorLogAdvisor;
import cn.followtry.common.monitor.core.MonitorLogPointCut;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.aop.Pointcut;

/**
 * @author followtry
 * @since 2022/7/13 2:42 下午
 */
public class MonitorLogPointCutTest {

    @Test
    public void testClassMatch() {
        MonitorLogPointCut pointCut = new MonitorLogPointCut();
        boolean matches = pointCut.getClassFilter().matches(TestHttpBean.class);
        Assert.assertTrue(matches);
    }

    @Test
    public void testClassNotMatch() {
        MonitorLogPointCut pointCut = new MonitorLogPointCut();
        boolean matches = pointCut.getClassFilter().matches(TestHttpNoMonitorBean.class);
        Assert.assertFalse(matches);
    }

    @Test
    public void testClassMatchAdvisor() {
        Pointcut pointcut = new MonitorLogAdvisor().getPointcut();
        boolean matches = pointcut.getClassFilter().matches(TestHttpBean.class);
        Assert.assertTrue(matches);
    }
}
