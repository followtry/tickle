package cn.followtry.common;

import cn.followtry.common.monitor.annotation.MonitorType;
import cn.followtry.common.monitor.core.MonitorLogAdvice;
import cn.followtry.common.monitor.log.HttpLoggerProcessor;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;


/**
 * @author followtry
 * @since 2022/7/13 11:23 上午
 */
public class HttpLoggerProcessorTest {

    @Test
    public void testMonitorType() {
        Assert.assertEquals(MonitorType.HTTP, HttpLoggerProcessor.getThis().getMonitorType());
    }

    @Test
    public void testTraceLog() throws Throwable {
        HttpLoggerProcessor.getThis();
        TestHttpBean target = new TestHttpBean();
        Method testMethod = TestHttpBean.class.getDeclaredMethod("testHttpMethod", Integer.class, HttpServletRequest.class);
        List<Object> interceptors = Collections.singletonList(new MonitorLogAdvice());
        int arg = 100;

        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpLoggerProcessor processor = Mockito.mock(HttpLoggerProcessor.class);
        Mockito.when(processor.getHttpServletRequest()).thenReturn(request);
        Mockito.when(request.getServletPath()).thenReturn("/local/test");
        Mockito.when(request.getRemoteHost()).thenReturn("192.168.200.100");
        Mockito.when(request.getLocalAddr()).thenReturn("127.0.0.1");
        FollowtryReflectiveMethodInvocation methodInvocation = new FollowtryReflectiveMethodInvocation(target, target, testMethod, new Object[]{arg, request}, TestHttpBean.class, interceptors);
        Object proceed = methodInvocation.proceed();
        Assert.assertEquals("print" + arg, proceed);
    }
}
