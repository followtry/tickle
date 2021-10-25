package cn.followtry.common.mybatis.sqllog;

import org.apache.ibatis.plugin.Interceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author followtry
 * @since 2021/8/12 11:17 上午
 */
public class SqlLogInterceptorProvider implements ObjectProvider<Interceptor[]>, ApplicationContextAware {

    private static final Logger log = LoggerFactory.getLogger(SqlLogInterceptorProvider.class);

    private Interceptor[] interceptors;
    
    private ApplicationContext applicationContext;

    public SqlLogInterceptorProvider(Interceptor[] interceptors) {
        this.interceptors = interceptors;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        setInterceptors();
    }

    private void setInterceptors() {
        Map<String, Interceptor> beansOfType = this.applicationContext.getBeansOfType(Interceptor.class);
        this.interceptors = beansOfType.values().toArray(new Interceptor[0]);
        log.info("inject interceptors, {}", Arrays.stream(interceptors).map(o -> o.getClass().getCanonicalName()).collect(Collectors.toList()));
    }

    @Override
    public Interceptor[] getObject() throws BeansException {
        return this.interceptors;
    }

    @Override
    public Interceptor[] getObject(Object... args) throws BeansException {
        return this.interceptors;
    }

    @Override
    public Interceptor[] getIfAvailable() throws BeansException {
        return this.interceptors;
    }

    @Override
    public Interceptor[] getIfUnique() throws BeansException {
        return this.interceptors;
    }
}
