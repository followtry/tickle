package cn.followtry.common.datasource;

import cn.followtry.common.datasource.core.*;
import cn.followtry.common.config.Constant;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.support.AbstractGenericPointcutAdvisor;
import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;

/**
 * @author followtry
 * @since 2022/9/4 10:41
 */
@ConditionalOnProperty(
        prefix = Constant.DS_KEY,
        name = {Constant.ENABLED}
)
@Configuration
@ConditionalOnClass({DataSource.class, StaticMethodMatcherPointcut.class, MethodInterceptor.class, AbstractGenericPointcutAdvisor.class, AbstractRoutingDataSource.class})
@EnableConfigurationProperties({DynamicDataSourceProperties.class})
public class DynamicDatasourceAutoConfiguration {

    @Bean(value = Constant.DYNAMIC_DATASOURCE_POINTCUT_NAME)
    @ConditionalOnMissingBean(value = DatasourcePointCut.class)
    public DatasourcePointCut datasourcePointCut() {
        DatasourcePointCut datasourcePointCut = new DatasourcePointCut();
        datasourcePointCut.setClassFilter(new DatasourceClassFilter(datasourcePointCut));
        return datasourcePointCut;
    }


    @Bean(value = Constant.DYNAMIC_DATASOURCE_ADVICE_NAME)
    @ConditionalOnMissingBean(value = DatasourceAdvice.class)
    public DatasourceAdvice datasourceAdvice(@Qualifier(value = Constant.DYNAMIC_DATASOURCE_POINTCUT_NAME) DatasourcePointCut datasourcePointCut) {
        return new DatasourceAdvice(datasourcePointCut);
    }

    @Bean(value = Constant.DYNAMIC_DATASOURCE_ADVISOR_NAME)
    @ConditionalOnMissingBean(value = DatasourceAdvisor.class)
    public DatasourceAdvisor datasourceAdvisor(@Qualifier(value = Constant.DYNAMIC_DATASOURCE_POINTCUT_NAME) DatasourcePointCut datasourcePointCut,
                                               @Qualifier(value = Constant.DYNAMIC_DATASOURCE_ADVICE_NAME) Advice advice) {
        return new DatasourceAdvisor(datasourcePointCut, advice);
    }

    @Primary
    @Bean(value = Constant.DYNAMIC_DATASOURCE_NAME)
    @ConditionalOnMissingBean(value = DynamicDataSource.class)
    public DynamicDataSource dynamicDataSource() {
        return new DynamicDataSource();
    }
}
