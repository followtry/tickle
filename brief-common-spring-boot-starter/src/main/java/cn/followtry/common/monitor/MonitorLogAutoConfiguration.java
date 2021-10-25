package cn.followtry.common.monitor;

import cn.followtry.common.config.Constant;
import cn.followtry.common.monitor.core.MonitorLogAdvice;
import cn.followtry.common.monitor.core.MonitorLogAdvisor;
import cn.followtry.common.utils.BeanDefinitionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.PriorityOrdered;

/**
 * @author followtry
 * @since 2021/7/29 11:38 上午
 */
@Configuration
@ConditionalOnProperty(
        prefix = Constant.MONITOR_LOG_KEY,
        name = {Constant.ENABLED},
        matchIfMissing = true
)
public class MonitorLogAutoConfiguration implements BeanDefinitionRegistryPostProcessor, BeanPostProcessor, ApplicationContextAware, PriorityOrdered {

    private static final Logger log = LoggerFactory.getLogger(MonitorLogAutoConfiguration.class);

    public static final String MONITOR_LOG_ADVICE_BEAN_NAME = MonitorLogAdvice.class.getCanonicalName();
    public static final String MONITOR_LOG_ADVISOR_BEAN_NAME = MonitorLogAdvisor.class.getCanonicalName();

    private ApplicationContext applicationContext;

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        BeanDefinitionBuilder definitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(MonitorLogAdvice.class);
        definitionBuilder.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
        BeanDefinitionUtils.registerBeanDefinitionIfAbsent(registry, MONITOR_LOG_ADVICE_BEAN_NAME, definitionBuilder.getBeanDefinition());

        BeanDefinitionBuilder advisorBuilder = BeanDefinitionBuilder.genericBeanDefinition(MonitorLogAdvisor.class);
        advisorBuilder.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
        advisorBuilder.addDependsOn(MONITOR_LOG_ADVICE_BEAN_NAME);
        BeanDefinitionUtils.registerBeanDefinitionIfAbsent(registry, MONITOR_LOG_ADVISOR_BEAN_NAME, advisorBuilder.getBeanDefinition());
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        //
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof MonitorLogAdvisor) {
            MonitorLogAdvisor monitorLogAdvisor = (MonitorLogAdvisor) bean;
            MonitorLogAdvice monitorLogAdvice = this.applicationContext.getBean(MONITOR_LOG_ADVICE_BEAN_NAME, MonitorLogAdvice.class);
            monitorLogAdvisor.setAdvice(monitorLogAdvice);
            log.info("inject advice. {}", MONITOR_LOG_ADVICE_BEAN_NAME);
        }
        return bean;
    }

    @Override
    public int getOrder() {
        return 10;
    }
}
