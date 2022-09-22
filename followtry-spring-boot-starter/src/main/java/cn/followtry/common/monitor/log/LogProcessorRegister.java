package cn.followtry.common.monitor.log;

import cn.followtry.common.monitor.annotation.MonitorType;

/**
 * @author followtry
 * @since 2022/7/14 3:02 下午
 */
public class LogProcessorRegister {

    public LogProcessorRegister() {
        init();
    }

    public void init() {
        DefaultLoggerMonitor.register(MonitorType.HTTP, HttpLoggerProcessor.getThis());
    }

    public void register(String key, LoggerMonitor loggerMonitor) {
        DefaultLoggerMonitor.register(key, loggerMonitor);
    }
}
