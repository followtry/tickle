package cn.followtry.mybatis.plugin;

import java.util.List;

/**
 * 存储日志信息
 * @author jingzhongzhi
 * @date 2017/10/23
 */
public class LogEntity {
    private List<Object> params;
    
    private String sql;
    
    private String invokeMethod;
    
    private Integer resultSize;

    public List<Object> getParams() {
        return params;
    }

    public void setParams(List<Object> params) {
        this.params = params;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getInvokeMethod() {
        return invokeMethod;
    }

    public void setInvokeMethod(String invokeMethod) {
        this.invokeMethod = invokeMethod;
    }

    public Integer getResultSize() {
        return resultSize;
    }

    public void setResultSize(Integer resultSize) {
        this.resultSize = resultSize;
    }

    @Override
    public String toString() {
        return "LogEntity{" +
                "invokeMethod='" + invokeMethod + '\'' +
                ", params=" + params +
                ", resultSize=" + resultSize +
                ", sql='" + sql + '\'' +
                '}';
    }
}
