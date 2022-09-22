package cn.followtry.common.datasource;

/**
 * @author followtry
 * @since 2022/9/4 10:42
 */
public class DynamicDataSourceItemProperties {

    /**
     * 数据源的bean名称
     */
    private String datasourceBeanName;

    /**
     * 匹配的基础包名，支持","分隔
     */
    private String matchBasePackage;


    public String getDatasourceBeanName() {
        return datasourceBeanName;
    }

    public void setDatasourceBeanName(String datasourceBeanName) {
        this.datasourceBeanName = datasourceBeanName;
    }

    public String getMatchBasePackage() {
        return matchBasePackage;
    }

    public void setMatchBasePackage(String matchBasePackage) {
        this.matchBasePackage = matchBasePackage;
    }
}
