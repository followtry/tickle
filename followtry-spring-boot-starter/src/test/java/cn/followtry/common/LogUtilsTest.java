package cn.followtry.common;

import cn.followtry.common.utils.LogUtils;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;

/**
 * @author followtry
 * @since 2022/9/22 16:52
 */
public class LogUtilsTest {

    @Test
    public void test01() {
        Object[] cus = {"122","zhangsan"};

        ArrayList<Object> objects = Lists.newArrayList();
        objects.add("HSF");
        objects.add("HHHH");
        objects.addAll(Lists.newArrayList(cus));
        LogUtils.monitor("MONITOR_LOG", objects.toArray());
    }
}
