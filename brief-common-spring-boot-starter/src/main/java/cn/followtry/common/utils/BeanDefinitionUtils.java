package cn.followtry.common.utils;

import cn.followtry.common.nacosconfig.register.NacosConfigRegisterComponent;
import cn.followtry.common.nacosconfig.register.NacosConfigScanComponent;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;

/**
 * @author followtry
 * @since 2021/10/9 7:16 下午
 */
public class BeanDefinitionUtils {

    public static void registerNacosConfigBeanDefinition(BeanDefinitionRegistry registry, String beanName) {
        if (registry.containsBeanDefinition(beanName)) {
            return;
        }
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(NacosConfigRegisterComponent.class);
        registry.registerBeanDefinition(beanName, builder.getBeanDefinition());
    }

    public static String generateBeanName(String clazzName, int index) {
        return clazzName + "#" + index;
    }

    public static void registerNacosConfigScanBeanDefinition(BeanDefinitionRegistry registry, String beanName, String basePackage) {
        if (registry.containsBeanDefinition(beanName)) {
            return;
        }
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(NacosConfigScanComponent.class);
        builder.addPropertyValue("basePackage",basePackage);
        registry.registerBeanDefinition(beanName, builder.getBeanDefinition());
    }

    public static void registerBeanDefinitionIfAbsent(BeanDefinitionRegistry registry, String beanName, AbstractBeanDefinition beanDefinition) {
        if (registry.containsBeanDefinition(beanName)) {
            return;
        }
        registry.registerBeanDefinition(beanName, beanDefinition);
    }
}
