package cn.followtry.common;

import cn.followtry.common.nacosconfig.convert.BizMapBeanConfigDataInject;
import cn.followtry.common.nacosconfig.model.BizTypeInfo;
import cn.followtry.common.utils.JsonUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

/**
 * @author followtry
 * @since 2021/10/22 3:05 下午
 */
public class BizMapBeanConfigDataInjectTest {

    @Test
    public void testDataInjectConvert() {
        String s = "{\"1\":{\"bizType\":1,\"bizName\":\"酒店\"},\"3\":{\"bizType\":3,\"bizName\":\"门票\"},\"20\":{\"bizType\":20,\"bizName\":\"加油\"},\"27\":{\"bizType\":27,\"bizName\":\"菲住卡\"},\"50\":{\"bizType\":50,\"bizName\":\"门票(直付通)\"}}";
        BizMapBeanConfigDataInject inject = new BizMapBeanConfigDataInject();
        Map<Integer, BizTypeInfo> stringBizTypeModelMap = inject.convertConfigData2Field(s);
        System.out.println(JsonUtils.toJson(stringBizTypeModelMap));
        Assert.assertTrue(true);
    }
}
