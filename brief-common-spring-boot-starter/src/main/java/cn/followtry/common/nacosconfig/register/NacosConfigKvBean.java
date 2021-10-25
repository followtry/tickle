package cn.followtry.common.nacosconfig.register;

import java.lang.reflect.Field;

/**
 * nacos-config的kv配置的元数据定义
 * @author followtry
 * @since 2021/9/23 3:20 下午
 */
public class NacosConfigKvBean {

    /**
     * 数据id
     */
    private String dataId;

    /**
     * 数据组
     */
    private String groupId;

    private Class<? extends NacosConfigDataListener> processor;

    private Field targetField;

    private Class<?> targetClazz;

    private Object targetBean;

    private String beanName;

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public Class<? extends NacosConfigDataListener> getProcessor() {
        return processor;
    }

    public void setProcessor(Class<? extends NacosConfigDataListener> processor) {
        this.processor = processor;
    }

    public Field getTargetField() {
        return targetField;
    }

    public void setTargetField(Field targetField) {
        this.targetField = targetField;
    }

    public Class<?> getTargetClazz() {
        return targetClazz;
    }

    public void setTargetClazz(Class<?> targetClazz) {
        this.targetClazz = targetClazz;
    }

    public Object getTargetBean() {
        return targetBean;
    }

    public void setTargetBean(Object targetBean) {
        this.targetBean = targetBean;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    @Override
    public String toString() {
        return "NacosConfigKvBean{" +
                "dataId='" + dataId + '\'' +
                ", groupId='" + groupId + '\'' +
                ", processor=" + processor +
                ", targetField=" + targetField +
                ", targetClazz=" + targetClazz +
                ", targetBean=" + targetBean +
                ", beanName='" + beanName + '\'' +
                '}';
    }
}
