package cn.followtry.common.utils;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 日志类
 */
public class LogUtils {


    private static final Logger LOG_SQL = LoggerFactory.getLogger("SQL_LOG");

    private static final Logger LOG_ERROR = LoggerFactory.getLogger("ERROR_LOG");

    /**
     * 日志分隔符
     */
    public static final String LOG_SPLIT_NEW = "##";
    public static final String DEFAULT_PLACEHOLDER = "-";


    public static void logSql(String sql, String param, long cost, Boolean result, boolean isSlowSql, String signature) {
        if (LOG_SQL.isInfoEnabled()) {
            LOG_SQL.info(logAppend(sql, param, cost, result, isSlowSql, signature));
        }
    }

    /**
     * <pre>
     *     日志格式：
     *     ##clientAppName##clientIp##localIp##servivceName.Method##cost##invokeStatus##request_json##resp_json##
     * </pre>
     *
     * @param dynamicLog
     */
    public static void monitorHsf(String dynamicLog, String logType, String clientAppName, String clientIp, String localIp, String serviceMethod, Long cost, Boolean invokeStatus, String resMsgValue, String reqArgs, String resp) {
        monitor(dynamicLog, logType, clientAppName, clientIp, localIp, serviceMethod, cost, invokeStatus, resMsgValue, reqArgs, resp);
    }

    /**
     * <pre>
     *     日志格式：
     *     ##-##clientIp##localIp##url##cost##invokeStatus##request_json##resp_json##
     * </pre>
     *
     * @param dynamicLog
     */
    public static void monitorHttp(String dynamicLog, String logType, String clientAppName, String clientIp, String localIp, String url, Long cost, Boolean invokeStatus, String resMsgValue, String reqArgs, String resp) {
        monitor(dynamicLog, logType, clientAppName, clientIp, localIp, url, cost, invokeStatus, resMsgValue, reqArgs, resp);
    }

    public static void monitor(String dynamicLog, String logType, String clientAppName, String clientIp, String localIp, String invokePath, Long cost, Boolean invokeStatus, String resMsgValue, String reqArgs, String resp) {
        Logger logger = LoggerFactory.getLogger(dynamicLog);
        logger.info(logAppend(logType, clientAppName, clientIp, localIp, invokePath, toStringOrDefault(cost), toStringOrDefault(invokeStatus), resMsgValue, reqArgs, resp));
    }

    public static void errLog(String key, String shortMsg) {
        errLog(key, shortMsg, null);
    }

    public static void errLog(String key, String shortMsg, Throwable e) {
        errLog(key, shortMsg, null, e);
    }

    public static void errLog(String key, String shortMsg, String tag1, String tagV1) {
        Map<String, String> tag = new HashMap<>();
        tag.put(tag1, tagV1);
        errLog(key, shortMsg, tag, null);
    }

    public static void errLog(String key, String shortMsg, Map<String, String> tags, Throwable e) {
        StringBuilder sb = new StringBuilder();
        if (tags != null) {
            for (Map.Entry<String, String> entry : tags.entrySet()) {
                String key1 = entry.getKey();
                String value = entry.getValue();
                sb.append(key1).append("=").append(value).append(LOG_SPLIT_NEW);
            }
        }
        String tagStr = sb.toString();
        LOG_ERROR.error(logAppend(key, shortMsg, tagStr), e);
    }

    private static String toStringOrDefault(Object source) {
        if (source == null) {
            return DEFAULT_PLACEHOLDER;
        }
        return source.toString();
    }

    private static String appendLog(String log) {
        return LOG_SPLIT_NEW + log;
    }

    private static String logAppend(Object... paras) {
        List<Object> paramCollect = Arrays.stream(paras).map(param -> Optional.ofNullable(param).orElse(DEFAULT_PLACEHOLDER)).collect(Collectors.toList());
        return appendLog(StringUtils.join(paramCollect, LOG_SPLIT_NEW));
    }


}
