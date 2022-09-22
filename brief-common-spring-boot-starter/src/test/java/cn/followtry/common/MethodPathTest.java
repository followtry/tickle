package cn.followtry.common;

import cn.followtry.common.utils.ValueUtil;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * @author followtry
 * @since 2022/5/20 1:30 下午
 */
public class MethodPathTest {

    @Test
    public void test01() throws NoSuchMethodException {
        Method method = this.getClass().getMethod("test01");
        String methodPath = ValueUtil.genSimpleMethodPath(method,null);
        System.out.println(methodPath);
        Assert.assertEquals("MethodPathTest.test01",methodPath);
    }

    @Test
    public void test02() throws NoSuchMethodException {
        Method method = this.getClass().getMethod("test01");
        String methodPath = ValueUtil.genSimpleMethodPath(method,"myMethod");
        System.out.println(methodPath);
        Assert.assertEquals("myMethod",methodPath);
    }
}
