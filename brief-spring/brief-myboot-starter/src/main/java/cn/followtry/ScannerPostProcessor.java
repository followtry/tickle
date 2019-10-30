package cn.followtry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;

/**
 * @author jingzhongzhi
 * @Description
 * @since 2019/10/30
 */
public class ScannerPostProcessor implements BeanDefinitionRegistryPostProcessor, EnvironmentAware, BeanFactoryAware {

    private static final Logger log = LoggerFactory.getLogger(ScannerPostProcessor.class);
    private ConfigurableEnvironment environment;
    private ConfigurableBeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (ConfigurableBeanFactory)beanFactory;
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        //将配置的 kv 绑定到指定的对象上
        MyBootScanProperties scanProperties = Binder.get(this.environment).bind("my-scanner", Bindable.of(MyBootScanProperties.class)).orElse(new MyBootScanProperties());
        BeanDefinition beanDefinition = this.buildScannerConfigurerBeanDefinition(scanProperties);
        //注册对象
        registry.registerBeanDefinition("scannerConfiguration",beanDefinition);

    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = (ConfigurableEnvironment)environment;
    }

    private BeanDefinition buildScannerConfigurerBeanDefinition(MyBootScanProperties myBootScanProperties) {
        BeanDefinitionBuilder scannerConfigurerBuilder = BeanDefinitionBuilder.genericBeanDefinition(ScannerConfiguration.class);
        scannerConfigurerBuilder.addPropertyValue("basePackage", myBootScanProperties.getBasePackage());
        return scannerConfigurerBuilder.getBeanDefinition();
    }
}
