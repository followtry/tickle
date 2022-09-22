package cn.followtry.common.monitor.log;

import cn.followtry.common.monitor.meta.MonitorLogMeta;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 默认的打印日志的格式
 * <pre>
 *     日志格式：
 *     ##clientAppName##clientIp##localIp##servivceName.Method##cost##invokeStatus##request_json##resp_json##
 * </pre>
 *
 * @author followtry
 * @since 2021/10/18 10:38 上午
 */
public class DefaultLoggerMonitor implements LoggerMonitor {

    private static final SpelExpressionParser spelExpressionParser = new SpelExpressionParser();

    private static final Map<String, LoggerMonitor> LOGGER_MONITOR_MAP = new ConcurrentHashMap<>();

    public static void register(String monitorType, LoggerMonitor loggerMonitor) {
        LOGGER_MONITOR_MAP.put(monitorType, loggerMonitor);
    }

    public static SpelExpressionParser getSpelExpressionParser() {
        return spelExpressionParser;
    }

    @Override
    public String getMonitorType() {
        return null;
    }

    @Override
    public Object invoke(MethodInvocation invocation, MonitorLogMeta monitorLogMeta) throws Throwable {
        Optional<LoggerMonitor> loggerMonitorOpt = getLoggerMonitor(monitorLogMeta.getTypeName());
        if (loggerMonitorOpt.isPresent()) {
            return loggerMonitorOpt.get().invoke(invocation, monitorLogMeta);
        }
        return invocation.proceed();
    }

    private Optional<LoggerMonitor> getLoggerMonitor(String monitorType) {
        if (monitorType == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(LOGGER_MONITOR_MAP.get(monitorType));
    }

}
