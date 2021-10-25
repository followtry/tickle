package cn.followtry.common.nacosconfig.convert;

import cn.followtry.common.nacosconfig.model.BizTypeInfo;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.Map;

/**
 * @author followtry
 * @since 2021/10/11 10:58 上午
 */
public class BizMapBeanConfigDataInject extends GenericConfigDataInject<Map<Integer, BizTypeInfo>> {

    @Override
    public TypeReference<Map<Integer, BizTypeInfo>> getTypeReference() {
        return new TypeReference<Map<Integer, BizTypeInfo>>(){};
    }
}
