package cn.followtry.common.mybatis.model;

import cn.followtry.common.config.Constant;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author followtry
 * @since 2022/9/2 17:32
 */
@ConfigurationProperties(prefix = Constant.MYBATIS_EXT_KEY)
public class MultiMybatisProperties {

    private List<MybatisItemProperties> properties;

    public List<MybatisItemProperties> getProperties() {
        return properties;
    }

    public void setProperties(List<MybatisItemProperties> properties) {
        this.properties = properties;
    }
}
