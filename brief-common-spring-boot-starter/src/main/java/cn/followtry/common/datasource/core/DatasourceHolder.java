package cn.followtry.common.datasource.core;

/**
 * @author followtry
 * @since 2021/3/11 9:22 下午
 */
public class DatasourceHolder {

    private static final ThreadLocal<String> datasourceBeanNameTl = new ThreadLocal<>();

    public static void setDataSource(String datasourceBeanName){
        datasourceBeanNameTl.set(datasourceBeanName);
    }

    public static String getDataSource(){
        return datasourceBeanNameTl.get();
    }

    public static void destory(){
        datasourceBeanNameTl.remove();
    }

}
