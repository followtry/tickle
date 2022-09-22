package cn.followtry.common.mybatis.sqllog;

import cn.followtry.common.utils.JsonUtil;
import cn.followtry.common.utils.LogUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Statement;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author followtry
 * @since 2021/8/12 10:42 上午
 */

@Intercepts(
        value = {
                @Signature(type = StatementHandler.class, method = "query", args = {Statement.class, ResultHandler.class}),
                @Signature(type = StatementHandler.class, method = "update", args = {Statement.class}),
        }
)
public class SqlLogInterceptor implements Interceptor {

    public static final String SEPARATOR = ".";
    private Long slowSqlTime;

    private static final Map<String, String> SQL_SIGN_MAP = new ConcurrentHashMap<>();

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        Object parameterObject = statementHandler.getBoundSql().getParameterObject();
        String sql = statementHandler.getBoundSql().getSql();
        String param = JsonUtil.toJson(parameterObject);
        String dbType = null;
        try {
            dbType = getDBType(invocation);
        } catch (Throwable throwable) {
            //ignore
        }

        long startTime = System.currentTimeMillis();
        long endTime = System.currentTimeMillis();
        boolean resSuc = true;
        Object proceed;
        try {
            proceed = invocation.proceed();
            endTime = System.currentTimeMillis();
        } catch (Exception e) {
            resSuc = false;
            endTime = System.currentTimeMillis();
            throw e;
        } finally {
            long cost = endTime - startTime;
            boolean isSlowSql = false;
            String signature = null;
            if (slowSqlTime < cost) {
                isSlowSql = true;
                signature = genSqlSignature(invocation, sql);
            }
            LogUtils.logSql(sql, param, cost, resSuc, isSlowSql, signature, dbType);
        }
        return proceed;
    }

    private String getDBType(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        if (args.length < 1) {
            return null;
        }
        Statement statement = (Statement) args[0];
        if (statement == null || statement.getConnection() == null) {
            return null;
        }

        Connection conn = statement.getConnection();
        return getCommonDbType(conn);
    }

    private String getCommonDbType(Connection conn) throws Throwable {
        String catalog = conn.getCatalog();
        if (conn.getMetaData() == null) {
            return catalog;
        }
        DatabaseMetaData metaData = conn.getMetaData();
        if (metaData == null) {
            return catalog;
        }
        String dbType = metaData.getDatabaseProductName();
        StringBuilder sb = new StringBuilder();
        if (StringUtils.isNotBlank(dbType)) {
            sb.append(dbType);
        }
        if (StringUtils.isNotBlank(conn.getCatalog())) {
            sb.append(SEPARATOR).append(catalog);
        }
        return sb.toString();
    }

    private String genSqlSignature(Invocation invocation, String sql) {
        Optional<String> signatureOpt = Optional.ofNullable(SQL_SIGN_MAP.get(sql));

        if (!signatureOpt.isPresent()) {
            try {
                StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
                Field delegate = statementHandler.getClass().getDeclaredField("delegate");
                ReflectionUtils.makeAccessible(delegate);
                StatementHandler statementHandlerV2 = (StatementHandler) delegate.get(statementHandler);
                Field mappedStatementField = statementHandlerV2.getClass().getSuperclass().getDeclaredField("mappedStatement");
                ReflectionUtils.makeAccessible(mappedStatementField);
                MappedStatement mappedStatement = (MappedStatement) mappedStatementField.get(statementHandlerV2);
                SQL_SIGN_MAP.put(sql, mappedStatement.getId());
                return mappedStatement.getId();
            } catch (NoSuchFieldException | IllegalAccessException e) {
                //ignore
                return null;
            }
        }
        return signatureOpt.get();
    }

    public Long getSlowSqlTime() {
        return slowSqlTime;
    }

    public void setSlowSqlTime(Long slowSqlTime) {
        this.slowSqlTime = slowSqlTime;
    }
}
