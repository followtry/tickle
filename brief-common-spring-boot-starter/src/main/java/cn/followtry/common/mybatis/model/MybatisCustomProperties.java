package cn.followtry.common.mybatis.model;

import cn.followtry.common.config.Constant;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author followtry
 * @since 2021/10/10 8:17 上午
 */
@ConfigurationProperties(Constant.MYBATIS_CUSTOM_KEY)
public class MybatisCustomProperties {

    /**
     * 慢Sql的超时时间，单位为毫秒
     */
    private Long slowSqlTime;

    public Long getSlowSqlTime() {
        return slowSqlTime;
    }

    public void setSlowSqlTime(Long slowSqlTime) {
        this.slowSqlTime = slowSqlTime;
    }
}
