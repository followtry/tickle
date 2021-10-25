package cn.followtry.common.monitor.meta;

import cn.followtry.common.monitor.annotation.MonitorType;
import cn.followtry.common.monitor.log.LoggerMonitor;

/**
 * @author followtry
 * @since 2021/10/13 8:36 下午
 */
public class MonitorLogMeta {

    private MonitorType typeName;

    private String logName = "MONITOR_LOG";

    /**
     * 默认的结果Y或N的表达式，使用SpEl表达式
     * 返回结果对象用resp表示
     */
    private String resExpress = "";

    /**
     * 方法简要声明，只对方法上的注解生效
     */
    private String methodSummary;

    /**
     * 是否需要打印请求日志
     */
    private Boolean needRequest = false;

    /**
     * 是否需要打印响应日志
     */
    private Boolean needResponse = false;

    private String resMsg;

    private String logProcessorBeanName;

    private LoggerMonitor loggerMonitorInstance;

    public MonitorLogMeta() {
    }


    public MonitorType getTypeName() {
        return typeName;
    }

    public void setTypeName(MonitorType typeName) {
        this.typeName = typeName;
    }

    public String getLogName() {
        return logName;
    }

    public void setLogName(String logName) {
        this.logName = logName;
    }

    public String getResExpress() {
        return resExpress;
    }

    public void setResExpress(String resExpress) {
        this.resExpress = resExpress;
    }

    public Boolean getNeedRequest() {
        return needRequest;
    }

    public void setNeedRequest(Boolean needRequest) {
        this.needRequest = needRequest;
    }

    public Boolean getNeedResponse() {
        return needResponse;
    }

    public void setNeedResponse(Boolean needResponse) {
        this.needResponse = needResponse;
    }


    public String getResMsg() {
        return resMsg;
    }

    public void setResMsg(String resMsg) {
        this.resMsg = resMsg;
    }

    public String getLogProcessorBeanName() {
        return logProcessorBeanName;
    }

    public void setLogProcessorBeanName(String logProcessorBeanName) {
        this.logProcessorBeanName = logProcessorBeanName;
    }

    public LoggerMonitor getLoggerMonitorInstance() {
        return loggerMonitorInstance;
    }

    public void setLoggerMonitorInstance(LoggerMonitor loggerMonitorInstance) {
        this.loggerMonitorInstance = loggerMonitorInstance;
    }

    public String getMethodSummary() {
        return methodSummary;
    }

    public void setMethodSummary(String methodSummary) {
        this.methodSummary = methodSummary;
    }

    @Override
    public String toString() {
        return "MonitorLogMeta{" +
                "typeName=" + typeName +
                ", logName='" + logName + '\'' +
                ", resExpress='" + resExpress + '\'' +
                ", methodSummary='" + methodSummary + '\'' +
                ", needRequest=" + needRequest +
                ", needResponse=" + needResponse +
                ", resMsg='" + resMsg + '\'' +
                ", logProcessorBeanName='" + logProcessorBeanName + '\'' +
                ", loggerMonitorInstance=" + loggerMonitorInstance +
                '}';
    }


}
