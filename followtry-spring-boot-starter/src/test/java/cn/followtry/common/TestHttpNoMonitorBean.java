package cn.followtry.common;

/**
 * @author followtry
 * @since 2022/7/13 11:40 上午
 */
public class TestHttpNoMonitorBean implements TestBeanInterface {

    @Override
    public String testMethod(Integer num) {
        return "print" + num;
    }

    public TestHttpNoMonitorBean() {
    }
}
