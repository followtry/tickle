package cn.followtry.common.nacosconfig.convert;

import cn.followtry.common.utils.JsonUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author followtry
 * @since 2021/9/23 4:52 下午
 */
public class ListStringConfigDataInject extends BaseNacosConfigDataInject<List<String>> {

    private static final Logger log = LoggerFactory.getLogger(ListStringConfigDataInject.class);

    @Override
    public List<String> convertConfigData2Field(String nacosConfigData) {
        List<String> values = JsonUtils.parseObject(nacosConfigData, new TypeReference<List<String>>(){});
        return values;
    }
}
