package cn.followtry.common;

import cn.followtry.common.monitor.annotation.MonitorLog;
import cn.followtry.common.monitor.annotation.MonitorType;

import javax.servlet.http.HttpServletRequest;

/**
 * @author followtry
 * @since 2022/7/13 11:40 上午
 */
@MonitorLog(typeName = MonitorType.HTTP, needRequest = true, needResponse = true)
public class TestHttpBean implements TestBeanInterface {

    @Override
    public String testMethod(Integer num) {
        return "print" + num;
    }

    public String testHttpMethod(Integer num, HttpServletRequest request) {
        return "print" + num;
    }

    public TestHttpBean() {
    }
}
