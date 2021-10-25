package cn.followtry.common.nacosconfig;

import cn.followtry.common.nacosconfig.anno.NacosConfigAgg;
import cn.followtry.common.nacosconfig.register.NacosConfigRegisterComponent;
import cn.followtry.common.config.Constant;
import cn.followtry.common.utils.BeanDefinitionUtils;
import com.alibaba.nacos.client.config.NacosConfigService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.PriorityOrdered;
import org.springframework.core.annotation.AnnotationUtils;

/**
 * @author followtry
 * @since 2021/10/9 6:09 下午
 */
@Configuration
@ConditionalOnClass({NacosConfigService.class})
@ConditionalOnProperty(
        prefix = Constant.NACOS_CONFIG_CUSTOM_KEY,
        name = {Constant.ENABLED},
        matchIfMissing = true
)
public class NacosConfigAutoConfiguration implements BeanDefinitionRegistryPostProcessor,BeanPostProcessor,ApplicationContextAware, PriorityOrdered {

    public static final int ORDER_NUM = 10;

    private NacosConfigRegisterComponent nacosConfigRegisterComponent;

    private ApplicationContext applicationContext;

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> clazz = bean.getClass();
        NacosConfigAgg nacosConfigAgg = AnnotationUtils.findAnnotation(clazz, NacosConfigAgg.class);
        if (nacosConfigAgg == null) {
            return bean;
        }
        if (this.nacosConfigRegisterComponent == null) {
            this.nacosConfigRegisterComponent = this.applicationContext.getBean(NacosConfigRegisterComponent.class);
        }
        nacosConfigRegisterComponent.registerNacosFromConfig(clazz);
        return bean;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        BeanDefinitionUtils.registerNacosConfigBeanDefinition(registry, BeanDefinitionUtils.generateBeanName(NacosConfigRegisterComponent.class.getCanonicalName(), 0));
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
    }

    @Override
    public int getOrder() {
        return ORDER_NUM;
    }
}
