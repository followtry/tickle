package cn.followtry.common.config;

/**
 * @author followtry
 * @since 2021/10/10 8:20 上午
 */
public interface Constant {
    String SPRING_PREFIX = "spring.";

    String MYBATIS_CUSTOM_KEY = SPRING_PREFIX + "mybatis.cus";
    String MONITOR_LOG_KEY = SPRING_PREFIX + "monitor.log";
    String MYBATIS__SLOW_SQL_PREFIX = SPRING_PREFIX + "mybatis.sql.log";
    String MYBATIS_EXT_KEY = SPRING_PREFIX + "mybatis.ext";
    String DS_KEY = SPRING_PREFIX + "datasource.dyna";
    String ENABLED = "enabled";

    String DYNAMIC_DATASOURCE_NAME = "dynamic_datasource";
    String DYNAMIC_DATASOURCE_ADVICE_NAME = DYNAMIC_DATASOURCE_NAME + "_advice";
    String DYNAMIC_DATASOURCE_POINTCUT_NAME = DYNAMIC_DATASOURCE_NAME + "_pointcut";
    String DYNAMIC_DATASOURCE_ADVISOR_NAME = DYNAMIC_DATASOURCE_NAME + "_advisor";
}
