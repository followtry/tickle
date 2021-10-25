package cn.followtry.common.nacosconfig.register;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author followtry
 * @since 2021/9/23 3:59 下午
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(NacosConfigScanRegistrar.class)
public @interface NacosConfigScan {

    /**
     * 扫描的包路径,多个路径逗号分隔
     * @return
     */
    String basePackages();
}
