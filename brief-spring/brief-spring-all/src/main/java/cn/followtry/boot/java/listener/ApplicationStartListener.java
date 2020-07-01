package cn.followtry.boot.java.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.*;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * 监听 Spring 应用启动的事件。
 * 事件由{@link EventPublishingRunListener} 发出，并由其广播给其持有的{@link org.springframework.context.ApplicationListener} 监听器，并由监听器实现实践的消费
 * 事件消费可以使用同步方式或者多线程异步方式进行
 * @author jingzhongzhi
 * @since 2020/6/27
 */
public class ApplicationStartListener implements EnvironmentPostProcessor, SmartApplicationListener, Ordered {

    private static final Logger log = LoggerFactory.getLogger(ApplicationStartListener.class);

    @Override
    public boolean supportsEventType(Class<? extends ApplicationEvent> eventType) {
        return ApplicationStartingEvent.class.isAssignableFrom(eventType)
                || ApplicationEnvironmentPreparedEvent.class.isAssignableFrom(eventType)
                || ApplicationContextInitializedEvent.class.isAssignableFrom(eventType)
                || ApplicationStartedEvent.class.isAssignableFrom(eventType)
                || ApplicationReadyEvent.class.isAssignableFrom(eventType)
                || ApplicationPreparedEvent.class.isAssignableFrom(eventType);
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ApplicationStartingEvent) {
            log.info("=================应用开始启动=======ApplicationStartingEvent=================");
        }
        if (event instanceof ApplicationEnvironmentPreparedEvent) {
            log.info("=================应用环境准备========ApplicationEnvironmentPreparedEvent================");
        }
        if (event instanceof ApplicationContextInitializedEvent) {
            log.info("=================应用上下文初始化=======ApplicationContextInitializedEvent=================");
        }
        if (event instanceof ApplicationStartedEvent) {
            log.info("=================应用已启动========ApplicationStartedEvent================");
        }
        if (event instanceof ApplicationReadyEvent) {
            log.info("=================应用正在运行========ApplicationReadyEvent================");
        }

        if (event instanceof ApplicationPreparedEvent) {
            log.info("=================应用准备中========ApplicationPreparedEvent================");
        }

    }

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {

    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 1;
    }
}
