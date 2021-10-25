package cn.followtry.common.monitor.meta;

import cn.followtry.common.monitor.annotation.MonitorType;
import cn.followtry.common.monitor.log.LoggerMonitor;

/**
 * @author followtry
 * @since 2021/10/25 4:10 下午
 */
public final class MonitorLogMetaBuilder {
    private MonitorType typeName;
    private String logName = "MONITOR_LOG";
    private String resExpress = "";
    private String methodSummary;
    private Boolean needRequest = false;
    private Boolean needResponse = false;
    private String resMsg;
    private String logProcessorBeanName;
    private LoggerMonitor loggerMonitorInstance;

    private MonitorLogMetaBuilder() {
    }

    public static MonitorLogMetaBuilder aMonitorLogMeta() {
        return new MonitorLogMetaBuilder();
    }

    public MonitorLogMetaBuilder withTypeName(MonitorType typeName) {
        this.typeName = typeName;
        return this;
    }

    public MonitorLogMetaBuilder withLogName(String logName) {
        this.logName = logName;
        return this;
    }

    public MonitorLogMetaBuilder withResExpress(String resExpress) {
        this.resExpress = resExpress;
        return this;
    }

    public MonitorLogMetaBuilder withMethodSummary(String methodSummary) {
        this.methodSummary = methodSummary;
        return this;
    }

    public MonitorLogMetaBuilder withNeedRequest(Boolean needRequest) {
        this.needRequest = needRequest;
        return this;
    }

    public MonitorLogMetaBuilder withNeedResponse(Boolean needResponse) {
        this.needResponse = needResponse;
        return this;
    }

    public MonitorLogMetaBuilder withResMsg(String resMsg) {
        this.resMsg = resMsg;
        return this;
    }

    public MonitorLogMetaBuilder withLogProcessorBeanName(String logProcessorBeanName) {
        this.logProcessorBeanName = logProcessorBeanName;
        return this;
    }

    public MonitorLogMetaBuilder withLoggerMonitorInstance(LoggerMonitor loggerMonitorInstance) {
        this.loggerMonitorInstance = loggerMonitorInstance;
        return this;
    }

    public MonitorLogMeta build() {
        MonitorLogMeta monitorLogMeta = new MonitorLogMeta();
        monitorLogMeta.setTypeName(typeName);
        monitorLogMeta.setLogName(logName);
        monitorLogMeta.setResExpress(resExpress);
        monitorLogMeta.setMethodSummary(methodSummary);
        monitorLogMeta.setNeedRequest(needRequest);
        monitorLogMeta.setNeedResponse(needResponse);
        monitorLogMeta.setResMsg(resMsg);
        monitorLogMeta.setLogProcessorBeanName(logProcessorBeanName);
        monitorLogMeta.setLoggerMonitorInstance(loggerMonitorInstance);
        return monitorLogMeta;
    }
}
