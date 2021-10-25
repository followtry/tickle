package cn.followtry.common.config;

/**
 * @author followtry
 * @since 2021/10/10 8:20 上午
 */
public abstract class Constant {
    public static final String SPRING_PREFIX = "spring.";

    public static final String MYBATIS_CUSTOM_KEY = SPRING_PREFIX + "mybatis.cus";
    public static final String NACOS_CONFIG_CUSTOM_KEY = SPRING_PREFIX + "nacos.config.cus";
    public static final String MONITOR_LOG_KEY = SPRING_PREFIX + "monitor.log";
    public static final String MYBATIS__SLOW_SQL_PREFIX = SPRING_PREFIX + "spring.mybatis.sql.log";
    public static final String ENABLED = "enabled";
}
