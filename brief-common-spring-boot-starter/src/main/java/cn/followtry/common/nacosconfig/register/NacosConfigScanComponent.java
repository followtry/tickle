package cn.followtry.common.nacosconfig.register;

import cn.followtry.common.nacosconfig.anno.NacosConfigAgg;
import cn.followtry.common.utils.BeanDefinitionUtils;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author followtry
 * @since 2021/10/9 7:46 下午
 */
public class NacosConfigScanComponent implements BeanDefinitionRegistryPostProcessor, BeanPostProcessor {

    private static final Logger log = LoggerFactory.getLogger(NacosConfigScanComponent.class);

    private static final ResourcePatternResolver RESOURCE_PATTERN_RESOLVER = new PathMatchingResourcePatternResolver();

    private static final MetadataReaderFactory METADATA_READER_FACTORY = new CachingMetadataReaderFactory();

    private String basePackage;

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        BeanDefinitionUtils.registerNacosConfigBeanDefinition(registry, BeanDefinitionUtils.generateBeanName(NacosConfigRegisterComponent.class.getCanonicalName(), 0));
    }

    private Set<Class<?>> scanClassesInAnnotation(String packagePatterns, List<Class<? extends Annotation>> tagAnnotations) throws IOException {
        Set<Class<?>> classes = new HashSet<>();
        String[] packagePatternArray = StringUtils.tokenizeToStringArray(packagePatterns, ConfigurableApplicationContext.CONFIG_LOCATION_DELIMITERS);
        for (String packagePattern : packagePatternArray) {
            Resource[] resources = RESOURCE_PATTERN_RESOLVER.getResources(ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX
                    + ClassUtils.convertClassNameToResourcePath(packagePattern) + "/**/*.class");
            for (Resource resource : resources) {
                try {
                    ClassMetadata classMetadata = METADATA_READER_FACTORY.getMetadataReader(resource).getClassMetadata();
                    Class<?> clazz = ClassUtils.forName(classMetadata.getClassName(), null);
                    for (Class<? extends Annotation> tagAnnotation : tagAnnotations) {
                        if (clazz.isAnnotationPresent(tagAnnotation)) {
                            classes.add(clazz);
                        }
                    }
                } catch (Throwable e) {
                    log.warn("Cannot load the '" + resource + "'. Cause by " + e.toString());
                }
            }
        }
        return classes;
    }

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        //
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof NacosConfigRegisterComponent) {
            NacosConfigRegisterComponent nacosConfigRegisterComponent = (NacosConfigRegisterComponent) bean;
            Set<Class<?>> clazzSet = null;
            try {
                clazzSet = scanClassesInAnnotation(basePackage, Lists.newArrayList(NacosConfigAgg.class));
            } catch (IOException e) {
                log.error("can not load candidateConfigClass. basePackage:{}", basePackage);
            }
            //在NacosConfigRegisterComponent初始化完成后在注册nacos-config的监听器,保证在调用registerNacosFromConfig方法前NacosConfigRegisterComponent已经完成实例化
            nacosConfigRegisterComponent.registerNacosFromConfig(clazzSet);
        }
        return bean;
    }
}
