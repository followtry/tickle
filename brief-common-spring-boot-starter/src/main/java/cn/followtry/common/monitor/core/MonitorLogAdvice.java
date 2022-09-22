package cn.followtry.common.monitor.core;

import cn.followtry.common.monitor.annotation.MonitorLog;
import cn.followtry.common.monitor.log.DefaultLoggerMonitor;
import cn.followtry.common.monitor.log.LoggerMonitor;
import cn.followtry.common.monitor.meta.MonitorLogMeta;
import cn.followtry.common.monitor.meta.MonitorLogMetaBuilder;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.*;

/**
 * 打印日志
 *
 * @author followtry
 * @since 2021/7/29 11:41 上午
 */
public class MonitorLogAdvice implements MethodInterceptor, ApplicationContextAware {

    public static final Class<MonitorLog> ANNOTATION_CLASS = MonitorLog.class;

    private static final LoggerMonitor DEFAULT_LOGGER_MONITOR = new DefaultLoggerMonitor();

    private ApplicationContext applicationContext;

    private static final Set<String> EXCLUDE_METHOD = new HashSet<>();

    static {
        EXCLUDE_METHOD.add("getClass");
        EXCLUDE_METHOD.add("hashCode");
        EXCLUDE_METHOD.add("equals");
        EXCLUDE_METHOD.add("clone");
        EXCLUDE_METHOD.add("toString");
        EXCLUDE_METHOD.add("notify");
        EXCLUDE_METHOD.add("notifyAll");
        EXCLUDE_METHOD.add("wait");
        EXCLUDE_METHOD.add("finalize");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public LoggerMonitor getLogMonitorBean(String logProcessorBeanName) {
        if (StringUtils.hasText(logProcessorBeanName)) {
            LoggerMonitor targetLoggerMonitor = this.applicationContext.getBean(logProcessorBeanName, LoggerMonitor.class);
            Objects.requireNonNull(targetLoggerMonitor, "class from[" + LoggerMonitor.class.getCanonicalName() + "] ,beanName[" + logProcessorBeanName + "] not exists in Spring Ioc");
            return targetLoggerMonitor;
        }
        return DEFAULT_LOGGER_MONITOR;
    }

    private boolean filter(MethodInvocation invocation) {
        Method method = invocation.getMethod();
        String name = method.getName();
        if (EXCLUDE_METHOD.contains(name)) {
            return true;
        }
        MonitorLog monitorLog = invocation.getThis().getClass().getAnnotation(ANNOTATION_CLASS);
        return monitorLog == null;
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        if (filter(invocation)) {
            return invocation.proceed();
        }

        MonitorLogMeta monitorLogMeta = mergeMonitorLogMeta(invocation);
        if (monitorLogMeta.getLoggerMonitorInstance() != null) {
            return monitorLogMeta.getLoggerMonitorInstance().invoke(invocation, monitorLogMeta);
        }
        return invocation.proceed();
    }

    private MonitorLogMeta mergeMonitorLogMeta(MethodInvocation invocation) {
        MonitorLogMetaBuilder logMetaBuilder = MonitorLogMetaBuilder.aMonitorLogMeta();
        Class<?> targetClazz = invocation.getThis().getClass();
        MonitorLog monitorLog = targetClazz.getAnnotation(ANNOTATION_CLASS);
        mergeAllLogMeta(monitorLog, logMetaBuilder, false);
        MonitorLog methodMonitorLog = invocation.getMethod().getAnnotation(ANNOTATION_CLASS);
        if (methodMonitorLog != null) {
            mergeAllLogMeta(methodMonitorLog, logMetaBuilder, true);
        }
        MonitorLogMeta monitorLogMeta = logMetaBuilder.build();
        LoggerMonitor logMonitorBean = getLogMonitorBean(monitorLogMeta.getLogProcessorBeanName());
        monitorLogMeta.setLoggerMonitorInstance(logMonitorBean);
        return monitorLogMeta;
    }

    private void mergeAllLogMeta(MonitorLog monitorLog, MonitorLogMetaBuilder logMetaBuilder, boolean isMethodLevel) {
        if (StringUtils.hasText(monitorLog.logName())) {
            logMetaBuilder.withLogName(monitorLog.logName());
        }

        if (StringUtils.hasText(monitorLog.resMsg())) {
            logMetaBuilder.withResMsg(monitorLog.resMsg());
        }

        if (StringUtils.hasText(monitorLog.resExpress())) {
            logMetaBuilder.withResExpress(monitorLog.resExpress());
        }

        if (monitorLog.reqExpress() != null && monitorLog.reqExpress().length > 0) {
            logMetaBuilder.withReqExpress(monitorLog.reqExpress());
        }

        if (monitorLog.needResponse()) {
            logMetaBuilder.withNeedResponse(true);
        }

        if (monitorLog.needRequest()) {
            logMetaBuilder.withNeedRequest(true);
        }

        if (StringUtils.hasText(monitorLog.logProcessor())) {
            logMetaBuilder.withLogProcessorBeanName(monitorLog.logProcessor());
        }
        logMetaBuilder.withTypeName(monitorLog.typeName()).withMethodSummary(isMethodLevel ? monitorLog.methodSummary() : null);
    }
}
