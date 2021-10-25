package cn.followtry.common.nacosconfig.register;

import cn.followtry.common.nacosconfig.NacosConfigDataInject;
import cn.followtry.common.nacosconfig.anno.NacosConfigAgg;
import cn.followtry.common.nacosconfig.anno.NacosConfigField;
import com.alibaba.nacos.api.config.ConfigService;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.CollectionUtils;

import javax.annotation.PreDestroy;
import java.lang.reflect.Field;
import java.util.*;

/**
 * @author followtry
 * @since 2021/9/23 2:57 下午
 */
public class NacosConfigRegisterComponent implements ApplicationContextAware {

    private static final Logger log = LoggerFactory.getLogger(NacosConfigRegisterComponent.class);


    private static final AnnotationFieldFilter annotationFieldFilter;

    static {
        annotationFieldFilter = new AnnotationFieldFilter(Lists.newArrayList(NacosConfigField.class));
    }

    private List<NacosConfigDataListener> nacosConfigListeners = new ArrayList();

    private static final Map<String, NacosConfigKvBean> REGISTERED_NACOS_CONFIG = new HashMap<>(16);

    private ApplicationContext applicationContext;
    //TODO by jingzhongzhi 待暴露实现方式
    private ConfigService configService = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public void registerNacosFromConfig(Class<?> clazz) {
        Set<Class<?>> clazzSet =  new HashSet<>();
        clazzSet.add(clazz);
        registerNacosFromConfig(clazzSet);
    }

    public void registerNacosFromConfig(Set<Class<?>> classSet) {
        Set<NacosConfigKvBean> nacosConfigKvBeans = parseAllNacosConfigField(classSet);
        List<NacosConfigKvBean> configKvBeans = filterRegisteredConfigBean(nacosConfigKvBeans);
        registerNacosConfigListener(configKvBeans);
    }

    private List<NacosConfigKvBean> filterRegisteredConfigBean(Set<NacosConfigKvBean> configKvBeans) {
        List<NacosConfigKvBean> newConfigKvBeans = new ArrayList<>(configKvBeans.size());
        for (NacosConfigKvBean configKvBean : configKvBeans) {
            String key = genConfigKey(configKvBean.getDataId(), configKvBean.getGroupId());
            if (!REGISTERED_NACOS_CONFIG.containsKey(key)) {
                newConfigKvBeans.add(configKvBean);
                REGISTERED_NACOS_CONFIG.put(key, configKvBean);
            }
        }
        return newConfigKvBeans;
    }

    private String genConfigKey(String dataId, String groupId) {
        return dataId + "__" + groupId;
    }

    private void registerNacosConfigListener(List<NacosConfigKvBean> nacosConfigKvBeans) {
        if (CollectionUtils.isEmpty(nacosConfigKvBeans)) {
            return;
        }
        for (NacosConfigKvBean configKvBean : nacosConfigKvBeans) {
            registerNacosConfigListener(configKvBean);
        }
    }

    private void registerNacosConfigListener(NacosConfigKvBean nacosConfigKvBean) {
        String dataId = resolve(nacosConfigKvBean.getDataId());
        String groupId = resolve(nacosConfigKvBean.getGroupId());
        Class<? extends NacosConfigDataListener> processor = nacosConfigKvBean.getProcessor();


        NacosConfigDataListener nacosConfigDataListener = BeanUtils.instantiateClass(processor);
        if (nacosConfigDataListener instanceof NacosConfigDataInject) {
            NacosConfigDataInject nacosConfigDataInject = (NacosConfigDataInject) nacosConfigDataListener;
            nacosConfigDataInject.setNacosConfigKvBean(nacosConfigKvBean);
        }
        log.info("register nacos-config dataId:{},groupId:{}", dataId, groupId);

        this.nacosConfigListeners.add(nacosConfigDataListener);
        try {
            String configData = configService.getConfigAndSignListener(dataId, groupId, 10000L,nacosConfigDataListener);
            nacosConfigDataListener.receiveConfigInfo(configData);
        } catch (Throwable var9) {
            String errorMsg = String.format("Error occur while fetching and parsing init config from nacos-config on dataId %s , groupId %s for callback class %s", dataId, groupId, nacosConfigDataListener.getClass().getName());
            log.error(errorMsg, var9);
            throw new IllegalStateException(errorMsg, var9);
        }
        log.info("register nacos-config listener on dataId {}, groupId {} for callback class {}", dataId, groupId, nacosConfigDataListener.getClass().getName());
    }

    private String resolve(String value) {
        return this.applicationContext.getEnvironment().resolvePlaceholders(value);
    }

    @PreDestroy
    public void destroy() {
        if (!CollectionUtils.isEmpty(nacosConfigListeners)) {
            for (NacosConfigDataListener listener : nacosConfigListeners) {
                log.info("remove nacos-config listener which listens on dataId {}, groupId {}.", listener.getDataId(), listener.getGroupId());
                configService.removeListener(listener.getDataId(), listener.getGroupId(), listener);
            }
        }
    }

    private Set<NacosConfigKvBean> parseAllNacosConfigField(Set<Class<?>> candidateConfigClass) {
        if (CollectionUtils.isEmpty(candidateConfigClass)) {
            return new HashSet<>();
        }
        Set<NacosConfigKvBean> allDkd = new HashSet<>();
        for (Class<?> configClass : candidateConfigClass) {
            NacosConfigAgg configAgg = configClass.getAnnotation(NacosConfigAgg.class);
            if (configAgg == null) {
                continue;
            }
            String beanName = configAgg.value();
            Object targetBean = applicationContext.getBean(beanName);
            Field[] declaredFields = configClass.getDeclaredFields();
            for (Field declaredField : declaredFields) {
                if (annotationFieldFilter.matches(declaredField)) {
                    NacosConfigField configField = declaredField.getAnnotation(NacosConfigField.class);
                    NacosConfigKvBean definition = new NacosConfigKvBean();
                    definition.setDataId(resolve(configField.dataId()));
                    definition.setGroupId(resolve(configField.groupId()));
                    definition.setProcessor(configField.processor());
                    definition.setTargetField(declaredField);
                    definition.setTargetClazz(configClass);
                    definition.setTargetBean(targetBean);
                    definition.setBeanName(beanName);
                    allDkd.add(definition);
                }
            }
        }
        return allDkd;
    }
}
