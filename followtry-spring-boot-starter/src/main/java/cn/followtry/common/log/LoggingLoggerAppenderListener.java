package cn.followtry.common.log;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.rolling.RollingFileAppender;
import ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy;
import ch.qos.logback.core.util.FileSize;
import org.slf4j.ILoggerFactory;
import org.slf4j.impl.StaticLoggerBinder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.boot.logging.LoggingSystem;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.GenericApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.core.ResolvableType;
import org.springframework.core.env.ConfigurableEnvironment;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author followtry
 * @since 2022/1/6 4:55 下午
 */
public class LoggingLoggerAppenderListener implements GenericApplicationListener {

    public static final String LOGBACK_CLASSIC_LOGGER_CONTEXT = "ch.qos.logback.classic.LoggerContext";
    private LoggingSystem loggingSystem;

    public static final int DEFAULT_ORDER = Ordered.HIGHEST_PRECEDENCE + 30;

    private static final String BASE_PATH = "/logs/common/";

    private static final String SQL_LOG_FILE_NAME = "sql_monitor.log";
    private static final String MONITOR_LOG_FILE_NAME = "monitor_log.log";

    private static final String ENCODER_PATTERN = "%d{yyyy-MM-dd HH:mm:ss} [%X{EAGLEEYE_TRACE_ID}] - %msg%n";

    private static final Class<?>[] EVENT_TYPES = {ApplicationStartingEvent.class,
            ApplicationEnvironmentPreparedEvent.class, ApplicationPreparedEvent.class, ContextClosedEvent.class,
            ApplicationFailedEvent.class};

    private static final Class<?>[] SOURCE_TYPES = {SpringApplication.class, ApplicationContext.class};

    private static final Map<String/*loggerName*/, String/*logFileName*/> LOGGER_NAME_FILE_MAP = new ConcurrentHashMap<>();

    static {
        LOGGER_NAME_FILE_MAP.put("SQL_LOG", SQL_LOG_FILE_NAME);
        LOGGER_NAME_FILE_MAP.put("MONITOR_LOG", MONITOR_LOG_FILE_NAME);
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ApplicationEnvironmentPreparedEvent) {
            onApplicationEnvironmentPreparedEvent((ApplicationEnvironmentPreparedEvent) event);
        }
    }

    private void onApplicationEnvironmentPreparedEvent(ApplicationEnvironmentPreparedEvent event) {
        ILoggerFactory factory = StaticLoggerBinder.getSingleton().getLoggerFactory();
        if (factory.getClass().getName().equalsIgnoreCase(LOGBACK_CLASSIC_LOGGER_CONTEXT)) {
            LoggerContext logbackLoggerContext = getLogbackLoggerContext(factory);
            String prefix = getUserHomePath(event);
            for (Map.Entry<String, String> entry : LOGGER_NAME_FILE_MAP.entrySet()) {
                String loggerName = entry.getKey();
                String loggerFileName = entry.getValue();

                //创建日志格式类
                PatternLayoutEncoder encoder = buildEncoder(logbackLoggerContext);
                //启动
                encoder.start();
                RollingFileAppender<ILoggingEvent> fileAppender = new RollingFileAppender<>();
                //创建滚动策略
                SizeAndTimeBasedRollingPolicy<ILoggingEvent> rollingPolicy = buildRollingPolicy(logbackLoggerContext, prefix, loggerFileName, fileAppender);
                rollingPolicy.start();
                //创建文件追加类
                buildFileAppender(logbackLoggerContext, prefix, loggerName, loggerFileName, encoder, fileAppender, rollingPolicy);

                //创建并设置logger的配置
                createAndSetLogger(logbackLoggerContext, loggerName, fileAppender);
            }
        }
    }

    private String getUserHomePath(ApplicationEnvironmentPreparedEvent event) {
        ConfigurableEnvironment environment = event.getEnvironment();
        String prefix = (String)environment.getSystemProperties().get("user.home");
        return prefix;
    }

    private void createAndSetLogger(LoggerContext logbackLoggerContext, String loggerName, RollingFileAppender<ILoggingEvent> fileAppender) {
        Logger logger = logbackLoggerContext.getLogger(loggerName);
        logger.addAppender(fileAppender);
        logger.setLevel(Level.INFO);
        logger.setAdditive(false);
    }

    private PatternLayoutEncoder buildEncoder(LoggerContext logbackLoggerContext) {
        PatternLayoutEncoder encoder = new PatternLayoutEncoder();
        encoder.setPattern(ENCODER_PATTERN);
        encoder.setCharset(StandardCharsets.UTF_8);
        encoder.setContext(logbackLoggerContext);
        encoder.setImmediateFlush(true);
        return encoder;
    }

    private void buildFileAppender(LoggerContext logbackLoggerContext, String prefix, String loggerName, String loggerFileName, PatternLayoutEncoder encoder, RollingFileAppender<ILoggingEvent> fileAppender, SizeAndTimeBasedRollingPolicy<ILoggingEvent> rollingPolicy) {
        fileAppender.setName(loggerName);
        fileAppender.setFile(prefix + BASE_PATH + loggerFileName);
        fileAppender.setContext(logbackLoggerContext);
        fileAppender.setAppend(true);
        fileAppender.setPrudent(false);
        fileAppender.setImmediateFlush(true);
        fileAppender.setRollingPolicy(rollingPolicy);
        fileAppender.setEncoder(encoder);
        fileAppender.start();
    }

    private SizeAndTimeBasedRollingPolicy<ILoggingEvent> buildRollingPolicy(LoggerContext logbackLoggerContext, String prefix, String loggerFileName, RollingFileAppender<ILoggingEvent> fileAppender) {
        SizeAndTimeBasedRollingPolicy<ILoggingEvent> rollingPolicy = new SizeAndTimeBasedRollingPolicy<>();
        rollingPolicy.setContext(logbackLoggerContext);
        rollingPolicy.setFileNamePattern(prefix + BASE_PATH + loggerFileName + ".%d.%i");
        rollingPolicy.setMaxHistory(30);
        rollingPolicy.setMaxFileSize(FileSize.valueOf("500MB"));
        rollingPolicy.setTotalSizeCap(FileSize.valueOf("30GB"));
        rollingPolicy.setParent(fileAppender);
        return rollingPolicy;
    }

    private LoggerContext getLogbackLoggerContext(ILoggerFactory factory) {
        return (LoggerContext) factory;
    }

    @Override
    public boolean supportsEventType(ResolvableType resolvableType) {
        return isAssignableFrom(resolvableType.getRawClass(), EVENT_TYPES);
    }

    private boolean isAssignableFrom(Class<?> type, Class<?>... supportedTypes) {
        if (type != null) {
            for (Class<?> supportedType : supportedTypes) {
                if (supportedType.isAssignableFrom(type)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean supportsSourceType(Class<?> sourceType) {
        return isAssignableFrom(sourceType, SOURCE_TYPES);
    }

    @Override
    public int getOrder() {
        return DEFAULT_ORDER;
    }
}
