package cn.followtry.common.mybatis;

import cn.followtry.common.config.Constant;
import cn.followtry.common.mybatis.model.MultiMybatisProperties;
import cn.followtry.common.mybatis.model.MybatisItemProperties;
import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.scripting.LanguageDriver;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.TypeHandler;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.mybatis.spring.boot.autoconfigure.MybatisLanguageDriverAutoConfiguration;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.beans.PropertyDescriptor;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 实现对Mybatis支持多数据源的扩展
 *
 * @author followtry
 * @since 2022/9/2 17:26
 */
@ConditionalOnProperty(
        prefix = Constant.MYBATIS_EXT_KEY,
        name = {Constant.ENABLED},
        matchIfMissing = false
)
@org.springframework.context.annotation.Configuration
@ConditionalOnClass({SqlSessionFactory.class, SqlSessionFactoryBean.class})
@ConditionalOnMissingBean(type = "org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration")
@ConditionalOnBean(DataSource.class)
@EnableConfigurationProperties(value = {MultiMybatisProperties.class})
@AutoConfigureAfter({MybatisCustomAutoConfiguration.class, MybatisLanguageDriverAutoConfiguration.class})
@Import(value = {
        MybatisAutoConfiguration.AutoConfiguredMapperScannerRegistrar.class
})
@Order(value = 10000)
public class MybatisExtAutoConfiguration implements InitializingBean, EnvironmentAware, BeanFactoryAware, ApplicationContextAware, ResourceLoaderAware, BeanDefinitionRegistryPostProcessor {


    private static final Logger log = LoggerFactory.getLogger(MybatisExtAutoConfiguration.class);

    private MultiMybatisProperties multiMybatisProperties;

    private Interceptor[] interceptors;

    private TypeHandler[] typeHandlers;

    private LanguageDriver[] languageDrivers;

    private ResourceLoader resourceLoader;

    private DatabaseIdProvider databaseIdProvider;

    private DefaultListableBeanFactory beanFactory;

    private ConfigurableEnvironment environment;

    private ApplicationContext ac;


    @Override
    public void setEnvironment(Environment environment) {
        this.environment = (ConfigurableEnvironment) environment;
        BindResult<MultiMybatisProperties> bindResult = Binder.get(this.environment).bind(Constant.MYBATIS_EXT_KEY, MultiMybatisProperties.class);
        if (bindResult.isBound()) {
            this.multiMybatisProperties = bindResult.get();
        }
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (DefaultListableBeanFactory) beanFactory;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.ac = applicationContext;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public void afterPropertiesSet() {
        checkConfigFileExists();
    }

    private void checkConfigFileExists() {
        if (CollectionUtils.isEmpty(multiMybatisProperties.getProperties())) {
            return;
        }
        for (MybatisItemProperties properties : multiMybatisProperties.getProperties()) {
            if (properties.isCheckConfigLocation() && StringUtils.hasText(properties.getConfigLocation())) {
                Resource resource = this.resourceLoader.getResource(properties.getConfigLocation());
                Assert.state(resource.exists(), String.format("Cannot find bean[%s] config location:  %s (please add config file or check your Mybatis configuration)", properties.getBeanNamePrefix(), resource));
            }
        }
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        if (CollectionUtils.isEmpty(multiMybatisProperties.getProperties())) {
            log.info("MultiMybatisProperties is empty");
            return;
        }
        init();
        for (int i = 0; i < multiMybatisProperties.getProperties().size(); i++) {
            MybatisItemProperties properties = multiMybatisProperties.getProperties().get(i);
            String beanNamePrefix = properties.getBeanNamePrefix();
            String datasourceBeanName = properties.getDatasourceBeanName();
            int index = i;
            Objects.requireNonNull(beanNamePrefix, () -> String.format("MybatisItemProperties[%s] config beanNamePrefix is null", index));
            Objects.requireNonNull(datasourceBeanName, () -> String.format("MybatisItemProperties[%s] config datasourceBeanName is null", index));
            String sessionFactoryBeanName = genSqlSessionFactoryBeanName(beanNamePrefix);
            String sessionTemplateBeanName = genSqlSessionTemplateBeanName(beanNamePrefix);
            String transactionManagerBeanName = genPlatformTransactionManagerBeanName(beanNamePrefix);
            beanFactory.registerBeanDefinition(sessionFactoryBeanName, createBeanDefinition(sessionFactoryBeanName, properties));
            beanFactory.registerBeanDefinition(sessionTemplateBeanName, createSqlSessionTemplateBeanDefinition(sessionFactoryBeanName, properties));
            beanFactory.registerBeanDefinition(transactionManagerBeanName, createPlatformTransactionManager(properties.getDatasourceBeanName()));
        }
    }


    public void init() {
        this.interceptors = Arrays.stream(ac.getBeanNamesForType(Interceptor.class)).map(o -> ac.getBean(o, Interceptor.class)).toArray(Interceptor[]::new);
        this.typeHandlers = Arrays.stream(ac.getBeanNamesForType(TypeHandler.class)).map(o -> ac.getBean(o, TypeHandler.class)).toArray(TypeHandler[]::new);
        this.languageDrivers = Arrays.stream(ac.getBeanNamesForType(LanguageDriver.class)).map(o -> ac.getBean(o, LanguageDriver.class)).toArray(LanguageDriver[]::new);

        Map<String, DatabaseIdProvider> databaseIdProviderMap = ac.getBeansOfType(DatabaseIdProvider.class);
        if (databaseIdProviderMap.size() > 0) {
            this.databaseIdProvider = ac.getBean(DatabaseIdProvider.class);
        }
    }


    private BeanDefinition createBeanDefinition(String beanName, MybatisItemProperties properties) {
        Objects.requireNonNull(properties.getDatasourceBeanName(), "datasourceBeanName不能为空");

        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(SqlSessionFactoryBean.class);
        builder.addPropertyReference("dataSource", properties.getDatasourceBeanName());
        builder.addPropertyValue("vfs", SpringBootVFS.class);
        if (StringUtils.hasText(properties.getConfigLocation())) {
            builder.addPropertyValue("configLocation", resourceLoader.getResource(properties.getConfigLocation()));
        }
        Configuration configuration = properties.getConfiguration();
        if (Objects.isNull(configuration) && !StringUtils.hasText(properties.getConfigLocation())) {
            configuration = new Configuration();
        }
        builder.addPropertyValue("configuration", configuration);

        if (ObjectUtils.isEmpty(properties.isShrinkWhitespacesInSql())) {
            configuration.setShrinkWhitespacesInSql(true);
        }

        if (Objects.nonNull(properties.getConfigurationProperties())) {
            builder.addPropertyValue("configurationProperties", properties.getConfigurationProperties());
        }

        if (!ObjectUtils.isEmpty(this.interceptors)) {
            builder.addPropertyValue("plugins", interceptors);
        }

        if (databaseIdProvider != null) {
            builder.addPropertyValue("databaseIdProvider", databaseIdProvider);
        }

        if (StringUtils.hasLength(properties.getTypeAliasesPackage())) {
            builder.addPropertyValue("typeAliasesPackage", properties.getTypeAliasesPackage());
        }
        if (Objects.nonNull(properties.getTypeAliasesSuperType())) {
            builder.addPropertyValue("typeAliasesSuperType", properties.getTypeAliasesSuperType());
        }

        if (StringUtils.hasLength(properties.getTypeHandlersPackage())) {
            builder.addPropertyValue("typeHandlersPackage", properties.getTypeHandlersPackage());
        }

        if (StringUtils.hasLength(properties.getTypeHandlersPackage())) {
            builder.addPropertyValue("typeHandlersPackage", properties.getTypeHandlersPackage());
        }
        if (!ObjectUtils.isEmpty(this.typeHandlers)) {
            builder.addPropertyValue("typeHandlers", typeHandlers);
        }

        builder.setScope(BeanDefinition.SCOPE_SINGLETON);

        if (!ObjectUtils.isEmpty(properties.resolveMapperLocations())) {
            builder.addPropertyValue("mapperLocations", properties.resolveMapperLocations());
        }

        Set<String> factoryPropertyNames = Stream
                .of(new BeanWrapperImpl(SqlSessionFactoryBean.class).getPropertyDescriptors()).map(PropertyDescriptor::getName)
                .collect(Collectors.toSet());
        Class<? extends LanguageDriver> defaultLanguageDriver = properties.getDefaultScriptingLanguageDriver();
        if (factoryPropertyNames.contains("scriptingLanguageDrivers") && !ObjectUtils.isEmpty(this.languageDrivers)) {
            // Need to mybatis-spring 2.0.2+
            builder.addPropertyValue("scriptingLanguageDrivers", languageDrivers);
            if (defaultLanguageDriver == null && this.languageDrivers.length == 1) {
                defaultLanguageDriver = this.languageDrivers[0].getClass();
            }
        }

        if (factoryPropertyNames.contains("defaultScriptingLanguageDriver")) {
            // Need to mybatis-spring 2.0.2+
            builder.addPropertyValue("defaultScriptingLanguageDriver", defaultLanguageDriver);
        }
        return builder.getBeanDefinition();
    }

    private BeanDefinition createSqlSessionTemplateBeanDefinition(String sqlSessionFactoryBeanName, MybatisItemProperties properties) {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(SqlSessionTemplate.class);
        if (Objects.isNull(properties.getExecutorType())) {
            builder.addConstructorArgReference(sqlSessionFactoryBeanName);
        } else {
            builder.addConstructorArgReference(sqlSessionFactoryBeanName);
            builder.addConstructorArgValue(properties.getExecutorType());
        }
        return builder.getBeanDefinition();
    }

    private BeanDefinition createPlatformTransactionManager(String datasourceBeanName) {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(DataSourceTransactionManager.class);
        builder.addConstructorArgReference(datasourceBeanName);
        return builder.getBeanDefinition();
    }

    private String genSqlSessionFactoryBeanName(String beanNamePrefix) {
        return beanNamePrefix + "SqlSessionFactory";
    }

    private String genSqlSessionTemplateBeanName(String beanNamePrefix) {
        return beanNamePrefix + "SqlSessionTemplate";
    }

    private String genPlatformTransactionManagerBeanName(String beanNamePrefix) {
        return beanNamePrefix + "TransactionManager";
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }
}
