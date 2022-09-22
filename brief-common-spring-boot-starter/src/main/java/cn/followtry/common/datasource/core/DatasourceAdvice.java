package cn.followtry.common.datasource.core;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.Optional;

/**
 * 打印日志
 *
 * @author followtry
 * @since 2021/7/29 11:41 上午
 */
public class DatasourceAdvice implements MethodInterceptor {

    private static final Logger log = LoggerFactory.getLogger(DatasourceAdvice.class);

    private DatasourcePointCut datasourcePointCut;

    public DatasourceAdvice(DatasourcePointCut datasourcePointCut) {
        this.datasourcePointCut = datasourcePointCut;
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        String dataSourceBeanName = getDataSourceBeanNameWithLock(invocation.getThis().getClass());
        if (StringUtils.hasText(dataSourceBeanName)) {
            try {
                setDatasourceInHolder(dataSourceBeanName);
                return invocation.proceed();
            } finally {
                DatasourceHolder.destory();
            }
        } else {
            return invocation.proceed();
        }
    }

    public void setDatasourceInHolder(String dataSourceBeanName) {
        DatasourceHolder.setDataSource(dataSourceBeanName);
    }

    public String getDataSourceBeanNameWithLock(Class<?> targetClazz) {
        Optional<String> optional = Optional.ofNullable(this.datasourcePointCut.getClassDataSourceCacheMap().get(targetClazz));
        return optional.orElseGet(() -> this.datasourcePointCut.getDataSourceBeanName(targetClazz));
    }
}
