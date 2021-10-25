package cn.followtry.common.nacosconfig.convert;

import cn.followtry.common.nacosconfig.register.NacosConfigKvBean;
import cn.followtry.common.nacosconfig.NacosConfigDataInject;
import cn.followtry.common.utils.JsonUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

/**
 * nacos-config数据注入的基类，除非功能扩展，新增功能，否则不可修改。
 * @author followtry
 * @since 2021/9/23 4:40 下午
 */
public abstract class BaseNacosConfigDataInject<T> implements NacosConfigDataInject<T> {

    private static final Logger log = LoggerFactory.getLogger(BaseNacosConfigDataInject.class);

    protected NacosConfigKvBean bean;

    @Override
    public void setNacosConfigKvBean(NacosConfigKvBean nacosConfigKvBean) {
        this.bean = nacosConfigKvBean;
    }

    @Override
    public void receiveConfigInfo(String configData) {
        if (StringUtils.isBlank(configData)) {
            log.warn("receive nacos-config data is blank. dataId:{},groupId:{}", bean.getDataId(), bean.getGroupId());
            return;
        }
        Object data2Field = convertConfigData2Field(configData);
        if (log.isInfoEnabled()) {
            log.info("dataId:{} groupId:{} nacos-config data :{}", bean.getDataId(), bean.getGroupId(), JsonUtils.toJson(data2Field));
        }
        Object targetBean = bean.getTargetBean();
        Field targetField = bean.getTargetField();
        injectData(data2Field, targetBean, targetField);
    }

    private void injectData(Object data2Field, Object targetBean, Field targetField) {
        ReflectionUtils.makeAccessible(targetField);
        try {
            if (log.isDebugEnabled()) {
                log.debug("beanName:{},field:{},data:{}", bean.getBeanName(), targetField.getName(), data2Field instanceof String ? data2Field : JsonUtils.toJson(data2Field));
            }
            targetField.set(targetBean, data2Field);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getDataId() {
        return this.bean.getDataId();
    }

    @Override
    public String getGroupId() {
        return this.bean.getGroupId();
    }
}
