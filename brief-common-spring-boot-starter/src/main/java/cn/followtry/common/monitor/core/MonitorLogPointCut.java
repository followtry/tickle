package cn.followtry.common.monitor.core;

import cn.followtry.common.monitor.annotation.MonitorLog;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.StaticMethodMatcherPointcut;

import java.lang.reflect.Method;

/**
 * @author followtry
 * @since 2021/7/29 11:51 上午
 */
public class MonitorLogPointCut extends StaticMethodMatcherPointcut {

    public MonitorLogPointCut() {
        super();

        setClassFilter(new MonitorLogClassFilter());
    }

    /**
     * 方法级别判断是否能匹配
     *
     * @param method      被判断的方法
     * @param targetClass 被判断的目标类
     * @return true为匹配
     */
    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        return true;
    }


    /**
     * 参考自 TransactionAttributeSourceClassFilter
     * <p>
     * 类级别的匹配过滤器
     */
    private static class MonitorLogClassFilter implements ClassFilter {
        @Override
        public boolean matches(Class<?> clazz) {
            return clazz.isAnnotationPresent(MonitorLog.class);
        }
    }

}
