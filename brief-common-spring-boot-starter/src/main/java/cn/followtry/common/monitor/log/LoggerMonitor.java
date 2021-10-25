package cn.followtry.common.monitor.log;

import cn.followtry.common.monitor.annotation.MonitorLog;
import cn.followtry.common.monitor.meta.MonitorLogMeta;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author followtry
 * @since 2021/10/18 10:36 上午
 */
public interface LoggerMonitor {

    /**
     * 自定义的日志拦截器
     * 比如可以发送到MQ
     * @param invocation 方法的调用封装
     * @param monitorLogMeta {@link MonitorLog} 注解的元数据
     * @return 调用结果
     * @throws Throwable 遇到异常时会抛出
     */
    Object invoke(MethodInvocation invocation, MonitorLogMeta monitorLogMeta) throws Throwable;
}
