package cn.followtry.common.nacosconfig.model;


/**
 * @author followtry
 * @since 2021/9/25 12:52 下午
 */
public class BizTypeInfo {
    /**
     * 业务类型
     */
    private Integer bizType;

    /**
     * 业务名称
     */
    private String bizName;

    public Integer getBizType() {
        return bizType;
    }

    public void setBizType(Integer bizType) {
        this.bizType = bizType;
    }

    public String getBizName() {
        return bizName;
    }

    public void setBizName(String bizName) {
        this.bizName = bizName;
    }

    @Override
    public String toString() {
        return "BizTypeInfo{" +
                "bizType=" + bizType +
                ", bizName='" + bizName + '\'' +
                '}';
    }
}
