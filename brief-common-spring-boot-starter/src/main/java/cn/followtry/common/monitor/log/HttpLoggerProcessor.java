package cn.followtry.common.monitor.log;

import cn.followtry.common.monitor.meta.MonitorLogMeta;
import cn.followtry.common.utils.LogUtils;
import cn.followtry.common.utils.ValueUtil;
import cn.followtry.common.monitor.annotation.MonitorType;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 处理HTTP的日志
 *
 * @author followtry
 * @since 2022/7/13 10:56 上午
 */
public class HttpLoggerProcessor implements LoggerMonitor {

    private static final SpelExpressionParser spelExpressionParser = DefaultLoggerMonitor.getSpelExpressionParser();

    private static final LoggerMonitor INSTANCE = new HttpLoggerProcessor();

    @Override
    public String getMonitorType() {
        return monitorType();
    }

    public static String monitorType() {
        return MonitorType.HTTP;
    }

    public static LoggerMonitor getThis() {
        return INSTANCE;
    }

    @Override
    public Object invoke(MethodInvocation invocation, MonitorLogMeta monitorLogMeta) throws Throwable {
        return traceHttpLogWithProceed(invocation, monitorLogMeta);
    }

    private Object traceHttpLogWithProceed(MethodInvocation invocation, MonitorLogMeta monitorLogMeta) throws Throwable {
        Object result = null;
        String loggerName = monitorLogMeta.getLogName();
        Object[] arguments = invocation.getArguments();
        Method method = invocation.getMethod();

        boolean invokeStatus = false;
        Exception exception = null;
        long startTime = System.currentTimeMillis();
        try {
            result = invocation.proceed();
            invokeStatus = true;
        } catch (Exception e) {
            exception = e;
            throw e;
        } finally {
            long endTime = System.currentTimeMillis();
            boolean realResSucStatus = ValueUtil.isRealResSucStatus(invokeStatus, result, monitorLogMeta.getResExpress());
            Object resMsgValue = ValueUtil.respValue(result, monitorLogMeta.getResMsg(), exception);
            List<Object> cusReqValues = Arrays.stream(monitorLogMeta.getReqExpress()).map(express -> ValueUtil.requestValue(method, arguments, express)).collect(Collectors.toList());

            long cost = endTime - startTime;
            String requestArg = monitorLogMeta.getNeedRequest() ? ValueUtil.buildRequestArgsJson(invocation) : null;
            String responseJson = monitorLogMeta.getNeedResponse() ? ValueUtil.buildResponseJson(result) : null;
            String httpPath = getHttpPath(monitorLogMeta.getMethodSummary());
            monitorLog(loggerName, realResSucStatus, resMsgValue, cusReqValues, cost, requestArg, responseJson, httpPath);
        }
        return result;
    }

    private void monitorLog(String loggerName, boolean realResSucStatus, Object resMsgValue, List<Object> cusReqValues, long cost, String requestArg, String responseJson, String httpPath) {
        List<Object> allArgs = new ArrayList<>();
        allArgs.add(monitorType());
        allArgs.add(null);
        allArgs.add(getRemoteClientIp());
        allArgs.add(getLocalIp());
        allArgs.add(httpPath);
        allArgs.add(cost);
        allArgs.add(realResSucStatus);
        allArgs.add(Objects.isNull(resMsgValue) ? null : resMsgValue.toString());
        allArgs.add(requestArg);
        allArgs.add(responseJson);
        allArgs.addAll(cusReqValues);
        LogUtils.monitor(loggerName, allArgs.toArray());
    }

    private String getHttpPath(String methodSummary) {
        if (!StringUtils.hasText(methodSummary)) {
            return methodSummary;
        }
        return getHttpPath();
    }
    private String getHttpPath() {
        HttpServletRequest request = getHttpServletRequest();
        return request == null ? null : request.getServletPath();
    }

    private String getRemoteClientIp() {
        HttpServletRequest request = getHttpServletRequest();
        return request == null ? null : request.getRemoteHost();
    }

    private String getLocalIp() {
        HttpServletRequest request = getHttpServletRequest();
        return request == null ? null : request.getLocalAddr();
    }

    public HttpServletRequest getHttpServletRequest() {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        if (sra == null) {
            return null;
        }
        return sra.getRequest();
    }
}
