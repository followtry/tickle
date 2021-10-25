package cn.followtry.common.nacosconfig.convert;

import cn.followtry.common.utils.JsonUtils;
import com.fasterxml.jackson.core.type.TypeReference;

/**
 * @author followtry
 * @since 2021/10/11 10:49 上午
 */
public abstract class GenericConfigDataInject<T> extends BaseNacosConfigDataInject<T> {

    public abstract TypeReference<T> getTypeReference();

    @Override
    public T convertConfigData2Field(String nacosConfigData) {
        return JsonUtils.parseObject(nacosConfigData,getTypeReference());
    }
}
