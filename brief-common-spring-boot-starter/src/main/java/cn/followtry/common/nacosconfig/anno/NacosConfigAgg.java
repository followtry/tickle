package cn.followtry.common.nacosconfig.anno;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * NacosConfig的聚合注解，可将多个NacosConfig配置放在同一个类中
 * @author followtry
 * @since 2021/9/23 3:15 下午
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface NacosConfigAgg {

    @AliasFor(annotation = Component.class)
    String value();
}
