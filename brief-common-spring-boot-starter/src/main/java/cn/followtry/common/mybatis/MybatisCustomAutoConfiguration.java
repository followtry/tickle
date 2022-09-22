package cn.followtry.common.mybatis;

import cn.followtry.common.config.Constant;
import cn.followtry.common.mybatis.model.MybatisCustomProperties;
import cn.followtry.common.mybatis.sqllog.SqlLogInterceptor;
import cn.followtry.common.mybatis.sqllog.SqlLogInterceptorProvider;
import cn.followtry.common.mybatis.typehandler.LocalDateTimeTypeHandler;
import cn.followtry.common.mybatis.typehandler.TypeHandlerProvider;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.mybatis.spring.boot.autoconfigure.MybatisLanguageDriverAutoConfiguration;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * @author followtry
 * @since 2021/10/10 8:11 上午
 */
@Configuration
@ConditionalOnClass({
        Interceptor.class,
        EnableAutoConfiguration.class,
        MybatisAutoConfiguration.class,
        MybatisLanguageDriverAutoConfiguration.class
})
@EnableConfigurationProperties({MybatisCustomProperties.class})
@ConditionalOnProperty(
        prefix = Constant.MYBATIS__SLOW_SQL_PREFIX,
        name = {Constant.ENABLED},
        matchIfMissing = true
)
@AutoConfigureBefore({MybatisLanguageDriverAutoConfiguration.class})
@Order(value = -10000)
public class MybatisCustomAutoConfiguration implements BeanDefinitionRegistryPostProcessor, ApplicationContextAware {

    private ApplicationContext applicationContext;

    private MybatisCustomProperties properties;

    public static final String SQL_LOG_INTERCEPTOR_BEAN_NAME = MybatisCustomAutoConfiguration.class.getCanonicalName() + "#" + "sqlLogInterceptor";
    public static final String SQL_LOG_INTERCEPTOR_PROVIDER_BEAN_NAME = MybatisCustomAutoConfiguration.class.getCanonicalName() + "#" + "sqlLogInterceptorProvider";

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
        registerInterceptors(beanDefinitionRegistry);
        registerInterceptorProvider(beanDefinitionRegistry);
        registerTypeHandlers(beanDefinitionRegistry);
        registerTypeHandlerProvider(beanDefinitionRegistry);
    }

    private void registerTypeHandlers(BeanDefinitionRegistry beanDefinitionRegistry) {
        BeanDefinitionBuilder definitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(LocalDateTimeTypeHandler.class);
        BeanDefinitionReaderUtils.registerWithGeneratedName(definitionBuilder.getBeanDefinition(),beanDefinitionRegistry);
    }

    private void registerTypeHandlerProvider(BeanDefinitionRegistry beanDefinitionRegistry) {
        BeanDefinitionBuilder definitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(TypeHandlerProvider.class);
        BeanDefinitionReaderUtils.registerWithGeneratedName(definitionBuilder.getBeanDefinition(),beanDefinitionRegistry);
    }

    private void registerInterceptors(BeanDefinitionRegistry beanDefinitionRegistry) {
        BeanDefinitionBuilder definitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(SqlLogInterceptor.class);
        definitionBuilder.addPropertyValue("slowSqlTime",properties.getSlowSqlTime());
        beanDefinitionRegistry.registerBeanDefinition(SQL_LOG_INTERCEPTOR_BEAN_NAME, definitionBuilder.getBeanDefinition());
    }

    private void registerInterceptorProvider(BeanDefinitionRegistry beanDefinitionRegistry) {
        BeanDefinitionBuilder definitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(SqlLogInterceptorProvider.class);
        beanDefinitionRegistry.registerBeanDefinition(SQL_LOG_INTERCEPTOR_PROVIDER_BEAN_NAME, definitionBuilder.getBeanDefinition());
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        BindResult<MybatisCustomProperties> bindResult = Binder.get(this.applicationContext.getEnvironment()).bind(Constant.MYBATIS_CUSTOM_KEY, MybatisCustomProperties.class);
        this.properties = bindResult.orElseGet(this::getDefaultProperties);
    }

    private MybatisCustomProperties getDefaultProperties() {
        MybatisCustomProperties properties = new MybatisCustomProperties();
        properties.setSlowSqlTime(100L);
        return properties;
    }
}
