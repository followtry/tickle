package cn.followtry.common.datasource.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.ClassFilter;
import org.springframework.util.StringUtils;

import java.util.Objects;

/**
 * 参考自 TransactionAttributeSourceClassFilter
 *
 * @author followtry
 * @since 2022/9/4 13:52
 */
public class DatasourceClassFilter implements ClassFilter {

    private static final Logger log = LoggerFactory.getLogger(DatasourceClassFilter.class);

    DatasourcePointCut datasourcePointCut;

    public DatasourceClassFilter(DatasourcePointCut datasourcePointCut) {
        this.datasourcePointCut = datasourcePointCut;
    }

    @Override
    public boolean matches(Class<?> clazz) {
        if (Objects.isNull(clazz.getCanonicalName())) {
            return false;
        }
        String dataSourceBeanName = datasourcePointCut.getDataSourceBeanName(clazz);
        return StringUtils.hasText(dataSourceBeanName);
    }
}
