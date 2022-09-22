package cn.followtry.common.datasource.core;

import cn.followtry.common.config.Constant;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.Map;

/**
 * 动态数据源管理
 * @author followtry
 * @since 2021/3/11 9:21 下午
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Autowired
    private Map<String, DataSource> dataSourceMap;

    @PostConstruct
    protected void initDataSource(){
        if (dataSourceMap != null && dataSourceMap.size() > 0) {
            Map<Object, Object> targetDataSources = Maps.newHashMapWithExpectedSize(dataSourceMap.size());
            for (Map.Entry<String, DataSource> dataSourceEntry : dataSourceMap.entrySet()) {
                String beanName = dataSourceEntry.getKey();
                DataSource dataSource = dataSourceEntry.getValue();
                if (ObjectUtils.notEqual(beanName, Constant.DYNAMIC_DATASOURCE_NAME)) {
                    targetDataSources.put(beanName, dataSource);
                }
            }
            super.setTargetDataSources(targetDataSources);
        }
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return DatasourceHolder.getDataSource();
    }


}
