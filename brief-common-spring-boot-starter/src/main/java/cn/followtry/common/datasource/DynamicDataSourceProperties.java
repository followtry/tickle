package cn.followtry.common.datasource;

import cn.followtry.common.config.Constant;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author followtry
 * @since 2022/9/4 10:42
 */
@ConfigurationProperties(value = Constant.DS_KEY)
public class DynamicDataSourceProperties {

    private List<DynamicDataSourceItemProperties> properties;

    public List<DynamicDataSourceItemProperties> getProperties() {
        return properties;
    }

    public void setProperties(List<DynamicDataSourceItemProperties> properties) {
        this.properties = properties;
    }
}
