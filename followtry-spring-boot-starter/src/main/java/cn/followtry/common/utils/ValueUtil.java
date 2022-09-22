package cn.followtry.common.utils;

import cn.followtry.common.monitor.log.DefaultLoggerMonitor;
import com.google.common.collect.Maps;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.http.HttpMessage;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Objects;

/**
 * @author followtry
 * @since 2022/9/22 15:42
 */
public class ValueUtil {

    private static final SpelExpressionParser spelExpressionParser = DefaultLoggerMonitor.getSpelExpressionParser();

    private static final LocalVariableTableParameterNameDiscoverer discoverer = new LocalVariableTableParameterNameDiscoverer();

    public static String genSimpleMethodPath(Method method, String customMethodName) {
        if (StringUtils.isNotBlank(customMethodName)) {
            return customMethodName;
        }
        String className = method.getDeclaringClass().getSimpleName();
        return className + "." + method.getName();
    }

    public static String buildRequestArgsJson(MethodInvocation invocation) {
        Object[] args = invocation.getArguments();
        if (args == null) {
            return null;
        }

        if (args.length == 1) {
            Object arg = args[0];
            return arg instanceof String ? (String) arg : JsonUtil.toJson(arg);
        }
        Map<String, Object> paramMap = Maps.newHashMap();
        for (int i = 0; i < args.length; i++) {
            Object arg = args[i];
            if (arg == null) {
                paramMap.put("arg" + i, null);
                continue;
            }

            if (arg instanceof ServletRequest || arg instanceof ServletResponse || arg instanceof HttpMessage) {
                arg = arg.toString();
            }
            paramMap.put("arg" + i, arg);
        }
        return JsonUtil.toJson(paramMap);
    }

    public static String buildResponseJson(Object result) {
        if (result instanceof String) {
            return (String) result;
        }
        if (result instanceof ServletResponse) {
            return result.toString();
        }
        return JsonUtil.toJson(result);
    }

    public static boolean isRealResSucStatus(boolean invokeStatus, Object result, String resExpress) {
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

    public static Object respValue(Object result, String resMsg, Exception e) {
        if (Objects.nonNull(e)) {
            //打印异常的msg
            return e.getMessage();
        }
        if (Objects.isNull(result)) {
            return null;
        }
        if (Objects.isNull(resMsg) || resMsg.length() == 0) {
            return null;
        }
        SpelExpression resMsgEx = spelExpressionParser.parseRaw(resMsg);
        return resMsgEx.getValue(result);
    }

    public static Object requestValue(Method method, Object[] args, String reqExpress) {
        if (Objects.isNull(reqExpress)) {
            return null;
        }
        String[] parameterNames = discoverer.getParameterNames(method);
        EvaluationContext context = new StandardEvaluationContext();
        for (int i = 0; i < Objects.requireNonNull(parameterNames).length; i++) {
            context.setVariable(parameterNames[i], args[i]);
        }

        Expression expression = spelExpressionParser.parseExpression(reqExpress.startsWith("#") ? reqExpress : "#" + reqExpress);
        return expression.getValue(context);
    }
}
