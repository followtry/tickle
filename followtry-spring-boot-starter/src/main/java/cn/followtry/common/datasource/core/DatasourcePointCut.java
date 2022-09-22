package cn.followtry.common.datasource.core;

import cn.followtry.common.config.Constant;
import cn.followtry.common.datasource.DynamicDataSourceItemProperties;
import cn.followtry.common.datasource.DynamicDataSourceProperties;
import com.google.common.collect.ImmutableMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author followtry
 * @since 2021/7/29 11:51 上午
 */
public class DatasourcePointCut extends StaticMethodMatcherPointcut implements EnvironmentAware, InitializingBean, ApplicationContextAware {

    private static final Logger log = LoggerFactory.getLogger(DatasourcePointCut.class);

    private Map<String/*basePackagePath*/, String/*datasourBeanName*/> dataSourceBeanNameMap = new ConcurrentHashMap<>();
    //缓存信息
    private Map<Class<?>, String/*datasourceBeanName*/> classDataSourceCacheMap = new ConcurrentHashMap<>();

    private Environment environment;

    private ApplicationContext context;

    public DatasourcePointCut() {
        super();
    }

    /**
     * 方法级别判断是否能匹配
     *
     * @param method      被判断的方法
     * @param targetClass 被判断的目标类
     * @return true为匹配
     */
    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        return true;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Binder.get(this.environment).bind(Constant.DS_KEY, DynamicDataSourceProperties.class).ifBound(this::initDataSourceProperties);
    }

    private void initDataSourceProperties(DynamicDataSourceProperties dynamicDataSourceProperties) {
        List<DynamicDataSourceItemProperties> itemPropertiesList = dynamicDataSourceProperties.getProperties();
        if (Objects.isNull(itemPropertiesList) || itemPropertiesList.size() == 0) {
            log.warn("dynamic datasource properties list is empty");
            return;
        }
        for (DynamicDataSourceItemProperties itemProperties : itemPropertiesList) {
            String matchBasePackage = itemProperties.getMatchBasePackage();
            String datasourceBeanName = itemProperties.getDatasourceBeanName();
            Objects.requireNonNull(datasourceBeanName, "dynamic datasource properties is empty");
            Objects.requireNonNull(matchBasePackage, () -> String.format("dynamic datasource[%s] properties matchBasePackage is empty", datasourceBeanName));
            String[] allBasePackage = matchBasePackage.split(",");
            for (String basePackage : allBasePackage) {
                if (dataSourceBeanNameMap.containsKey(basePackage)) {
                    String existDatasourceBeanName = dataSourceBeanNameMap.get(basePackage);
                    if (!Objects.equals(existDatasourceBeanName, datasourceBeanName)) {
                        throw new IllegalArgumentException(String.format("basePackage[%s] match more than one datasource. datasource names are %s,%s", basePackage, datasourceBeanName, existDatasourceBeanName));
                    }
                } else {
                    dataSourceBeanNameMap.put(basePackage, datasourceBeanName);
                }
            }
        }
    }

    public Map<String, String> getDataSourceBeanNameMap() {
        return ImmutableMap.copyOf(dataSourceBeanNameMap);
    }

    public void setDataSourceBeanNameMap(Map<String, String> dataSourceBeanNameMap) {
        this.dataSourceBeanNameMap = dataSourceBeanNameMap;
    }

    public Map<Class<?>, String> getClassDataSourceCacheMap() {
        return ImmutableMap.copyOf(classDataSourceCacheMap);
    }

    public void cacheDatasourceClassMap(Class<?> clazz, String datasourceBeanName) {
        this.classDataSourceCacheMap.put(clazz, datasourceBeanName);
    }

    public String getDataSourceBeanName(Class<?> targetClazz) {
        Map<Class<?>, String> classDataSourceCacheMap = getClassDataSourceCacheMap();
        //判断接口是否存在数据源
        Optional<String> optional = Optional.ofNullable(classDataSourceCacheMap.get(targetClazz));
        if (optional.isPresent()) {
            return optional.get();
        }
        //判断接口是否存在数据源
        for (Class<?> anInterface : targetClazz.getInterfaces()) {
            String datasourceName = classDataSourceCacheMap.get(anInterface);
            if (StringUtils.hasText(datasourceName)) {
                return datasourceName;
            }
        }

        String canonicalName = targetClazz.getCanonicalName();
        for (Map.Entry<String, String> entry : getDataSourceBeanNameMap().entrySet()) {
            String basePackagePath = entry.getKey();
            String datasourceBeanName = entry.getValue();
            if (canonicalName.startsWith(basePackagePath)) {
                cacheDatasourceClassMap(targetClazz, datasourceBeanName);
                return datasourceBeanName;
            }
            for (Class<?> anInterface : targetClazz.getInterfaces()) {
                if (anInterface.getCanonicalName().startsWith(basePackagePath)) {
                    cacheDatasourceClassMap(anInterface, datasourceBeanName);
                    return datasourceBeanName;
                }
            }
        }
        return null;
    }
}
