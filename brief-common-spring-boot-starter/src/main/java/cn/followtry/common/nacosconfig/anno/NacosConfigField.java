package cn.followtry.common.nacosconfig.anno;


import cn.followtry.common.nacosconfig.NacosConfigDataInject;

import java.lang.annotation.*;

/**
 * @author followtry
 * @since 2021/9/23 2:46 下午
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NacosConfigField {
    String dataId();

    String groupId();

    String executor() default "";

    /**
     * 具体业务处理器
     * @return
     */
    Class<? extends NacosConfigDataInject> processor() default NacosConfigDataInject.class;
}
