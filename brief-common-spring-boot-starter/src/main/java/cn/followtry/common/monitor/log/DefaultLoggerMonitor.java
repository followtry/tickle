package cn.followtry.common.monitor.log;

import cn.followtry.common.monitor.annotation.MonitorType;
import cn.followtry.common.monitor.meta.MonitorLogMeta;
import cn.followtry.common.utils.JsonUtils;
import cn.followtry.common.utils.LogUtils;
import com.alibaba.fastjson.JSONObject;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.expression.spel.standard.SpelExpression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.HttpRequest;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * 默认的打印日志的格式
 * <pre>
 *     日志格式：
 *     ##clientAppName##clientIp##localIp##servivceName.Method##cost##invokeStatus##request_json##resp_json##
 * </pre>
 *
 * @author followtry
 * @since 2021/10/18 10:38 上午
 */
public class DefaultLoggerMonitor implements LoggerMonitor {

    private final SpelExpressionParser spelExpressionParser = new SpelExpressionParser();

    @Override
    public Object invoke(MethodInvocation invocation, MonitorLogMeta monitorLogMeta) throws Throwable {

//        if (Objects.equals(monitorLogMeta.getTypeName(), MonitorType.HSF)) {
//            return traceHsfLogWithProceed(invocation, monitorLogMeta);
//        }
        if (Objects.equals(monitorLogMeta.getTypeName(), MonitorType.HTTP)) {
            return traceHttpLogWithProceed(invocation, monitorLogMeta);
        }
        return invocation.proceed();
    }

    private boolean isRealResSucStatus(boolean invokeStatus, Object result, String resExpress) {
        if (!invokeStatus) {
            return false;
        }

        if (result == null) {
            return true;
        }

        if (resExpress != null && resExpress.length() > 0) {
            SpelExpression spelExpression = spelExpressionParser.parseRaw(resExpress);
            Boolean resValue = spelExpression.getValue(result, Boolean.class);
            return Objects.equals(resValue, true);
        }
        return true;
    }

    private String parseResMsg(Object result, String resMsg, Exception e) {
        if (e != null) {
            //打印异常的msg
            return e.getMessage();
        }
        if (result == null) {
            return null;
        }
        if (resMsg == null || resMsg.length() == 0) {
            return null;
        }
        SpelExpression resMsgEx = spelExpressionParser.parseRaw(resMsg);
        String resMsgValue = resMsgEx.getValue(result, String.class);

        return StringUtils.isEmpty(resMsgValue) ? null : resMsgValue;
    }

    private Object traceHttpLogWithProceed(MethodInvocation invocation, MonitorLogMeta monitorLogMeta) throws Throwable {
        Object result = null;
        boolean needRequest = monitorLogMeta.getNeedRequest();
        boolean needResponse = monitorLogMeta.getNeedResponse();
        String loggerName = monitorLogMeta.getLogName();
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
            boolean realResSucStatus = isRealResSucStatus(invokeStatus, result, monitorLogMeta.getResExpress());
            String resMsgValue = parseResMsg(result, monitorLogMeta.getResMsg(), exception);
            long cost = endTime - startTime;
            String requestArg = needRequest ? buildRequestArgsJson(invocation) : null;
            String responseJson = needResponse ? buildResponseJson(result) : null;
            String httpPath = getHttpPath();
            if (!StringUtils.isEmpty(monitorLogMeta.getMethodSummary())){
                httpPath = monitorLogMeta.getMethodSummary();
            }
            LogUtils.monitorHttp(loggerName, MonitorType.HTTP.name(), null, getRemoteClientIp(), getLocalIp(), httpPath, cost, realResSucStatus, resMsgValue, requestArg, responseJson);
        }
        return result;
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

    private HttpServletRequest getHttpServletRequest() {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        if (sra == null) {
            return null;
        }
        return sra.getRequest();
    }

//    private Object traceHsfLogWithProceed(MethodInvocation invocation, MonitorLogMeta monitorLogMeta) throws Throwable {
//        Object result = null;
//        boolean needRequest = monitorLogMeta.getNeedRequest();
//        boolean needResponse = monitorLogMeta.getNeedResponse();
//        String loggerName = monitorLogMeta.getLogName();
//        String clientAppName = RequestCtxUtil.getAppNameOfClient();
//        String realClientIp = RequestCtxUtil.getRealClientIp();
//        String localIp = RequestCtxUtil.getLocalIp();
//        String qualifiedMethodName = ClassUtils.getQualifiedMethodName(invocation.getMethod());
//        if (!StringUtils.isEmpty(monitorLogMeta.getMethodSummary())){
//            qualifiedMethodName = monitorLogMeta.getMethodSummary();
//        }
//        boolean invokeStatus = false;
//        Exception exception = null;
//        long startTime = System.currentTimeMillis();
//        try {
//            result = invocation.proceed();
//            invokeStatus = true;
//        } catch (Exception e) {
//            exception = e;
//            throw e;
//        } finally {
//            long endTime = System.currentTimeMillis();
//            long cost = endTime - startTime;
//            boolean realResSucStatus = isRealResSucStatus(invokeStatus, result, monitorLogMeta.getResExpress());
//            String resMsgValue = parseResMsg(result, monitorLogMeta.getResMsg(), exception);
//            String requestArg = needRequest ? buildRequestArgsJson(invocation) : null;
//            String responseJson = needResponse ? buildResponseJson(result) : null;
//            LogUtils.monitorHsf(loggerName, MonitorType.HSF.name(), clientAppName, realClientIp, localIp, qualifiedMethodName, cost, realResSucStatus, resMsgValue, requestArg, responseJson);
//        }
//        return result;
//    }


    private String buildResponseJson(Object result) {
        return result instanceof String ? (String) result : JsonUtils.toJson(result);
    }

    private String buildRequestArgsJson(MethodInvocation invocation) {
        Object[] args = invocation.getArguments();
        if (args == null) {
            return null;
        }

        if (args.length == 1) {
            Object arg = args[0];
            return arg instanceof String ? (String) arg : JsonUtils.toJson(arg);
        }
        JSONObject paramMap = new JSONObject();
        for (int i = 0; i < args.length; i++) {
            Object arg = args[i];
            if (arg == null) {
                paramMap.put("arg" + i, null);
                continue;
            }

            if (arg instanceof HttpServletRequest || arg instanceof HttpServletResponse || arg instanceof HttpRequest || arg instanceof HttpOutputMessage) {
                arg = arg.toString();
            }
            paramMap.put("arg" + i, arg);
        }
        return JSONObject.toJSONString(paramMap);
    }
}
