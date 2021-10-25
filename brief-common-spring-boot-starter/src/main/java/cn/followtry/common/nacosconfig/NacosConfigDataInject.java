package cn.followtry.common.nacosconfig;

import cn.followtry.common.nacosconfig.register.NacosConfigDataListener;
import cn.followtry.common.nacosconfig.register.NacosConfigKvBean;

/**
 * @author followtry
 * @since 2021/9/23 4:37 下午
 */
public interface NacosConfigDataInject<T> extends NacosConfigDataListener {

    void setNacosConfigKvBean(NacosConfigKvBean nacosConfigKvBean);

    T convertConfigData2Field(String nacosConfigData);

}
