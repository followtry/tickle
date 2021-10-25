package cn.followtry.common.nacosconfig.register;

import com.alibaba.nacos.api.config.listener.Listener;

import java.util.concurrent.Executor;

/**
 * @author followtry
 * @since 2021/10/25 8:20 下午
 */
public interface NacosConfigDataListener extends Listener {

    @Override
    default Executor getExecutor(){
        return null;
    }

    String getDataId();

    String getGroupId();

}
