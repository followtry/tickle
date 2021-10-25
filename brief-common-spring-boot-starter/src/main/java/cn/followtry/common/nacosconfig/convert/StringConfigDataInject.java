package cn.followtry.common.nacosconfig.convert;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author followtry
 * @since 2021/9/23 4:52 下午
 */
public class StringConfigDataInject extends BaseNacosConfigDataInject<String> {

    private static final Logger log = LoggerFactory.getLogger(StringConfigDataInject.class);

    @Override
    public String convertConfigData2Field(String nacosConfigData) {
        return nacosConfigData;
    }
}
