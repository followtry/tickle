package cn.followtry.common.utils;

import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;

/**
 * @author followtry
 * @since 2021/10/9 7:16 下午
 */
public class BeanDefinitionUtils {

    public static void registerBeanDefinitionIfAbsent(BeanDefinitionRegistry registry, String beanName, AbstractBeanDefinition beanDefinition) {
        if (registry.containsBeanDefinition(beanName)) {
            return;
        }
        registry.registerBeanDefinition(beanName, beanDefinition);
    }
}
