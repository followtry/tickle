package cn.followtry;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author jingzhongzhi
 * @Description
 * @since 2019/10/17
 */
@ConfigurationProperties(
        prefix = "my-scanner"
)
public class MyBootScanProperties {
    public static final String MY_BOOT_PREFIX = "my-scanner";

    /**  */
    private String basePackage = ".";

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }
}
