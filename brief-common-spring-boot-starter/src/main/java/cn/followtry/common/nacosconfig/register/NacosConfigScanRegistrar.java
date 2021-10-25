package cn.followtry.common.nacosconfig.register;

import cn.followtry.common.utils.BeanDefinitionUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author followtry
 * @since 2021/9/23 4:01 下午
 */
public class NacosConfigScanRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        AnnotationAttributes mapperScanAttrs = AnnotationAttributes
                .fromMap(importingClassMetadata.getAnnotationAttributes(NacosConfigScan.class.getName()));
        if (mapperScanAttrs != null) {
            String basePackage = mapperScanAttrs.getString("basePackages");
            String beanName = BeanDefinitionUtils.generateBeanName(NacosConfigScanComponent.class.getCanonicalName(), 0);
            BeanDefinitionUtils.registerNacosConfigScanBeanDefinition(registry, beanName,basePackage);
        }
    }
}
