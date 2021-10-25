package cn.followtry.common.mybatis.typehandler;

import org.apache.ibatis.type.TypeHandler;
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
 * @since 2021/10/10 08:59 上午
 */
public class TypeHandlerProvider implements ObjectProvider<TypeHandler[]>, ApplicationContextAware {

    private static final Logger log = LoggerFactory.getLogger(TypeHandlerProvider.class);

    private TypeHandler[] typeHandlers;

    private ApplicationContext applicationContext;

    public TypeHandlerProvider(TypeHandler[] interceptors) {
        this.typeHandlers = interceptors;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        setInterceptors();
    }

    private void setInterceptors() {
        Map<String, TypeHandler> beansOfType = this.applicationContext.getBeansOfType(TypeHandler.class);
        this.typeHandlers = beansOfType.values().toArray(new TypeHandler[0]);
        log.info("inject typeHandlers, {}", Arrays.stream(typeHandlers).map(o -> o.getClass().getCanonicalName()).collect(Collectors.toList()));
    }

    @Override
    public TypeHandler[] getObject() throws BeansException {
        return this.typeHandlers;
    }

    @Override
    public TypeHandler[] getObject(Object... args) throws BeansException {
        return this.typeHandlers;
    }

    @Override
    public TypeHandler[] getIfAvailable() throws BeansException {
        return this.typeHandlers;
    }

    @Override
    public TypeHandler[] getIfUnique() throws BeansException {
        return this.typeHandlers;
    }
}
