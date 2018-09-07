/**
 * 
 */
package cn.followtry.mybatis.plugin;

import org.apache.ibatis.executor.statement.BaseStatementHandler;
import org.apache.ibatis.executor.statement.PreparedStatementHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.SqlSessionFactory;

import java.lang.reflect.Field;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

/**
 * 插件的执行顺序依据其在配置文件中由上到下的顺序，如果想在最先或最后执行插件，就将插件配置在plugins标签的最前或最后
 * @author jingzz
 * @time 2016年10月19日 下午1:35:52
 * @since 2016年10月19日 下午1:35:52
 */
@Intercepts({@Signature(type = StatementHandler.class, method ="query", args = {Statement.class,ResultHandler.class})})
public class LogPlugin implements Interceptor {
	
	private SqlSessionFactory sqlSessionFactory;
	
	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}
	
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		Object target = invocation.getTarget();
		if (target instanceof RoutingStatementHandler) {
			
			StatementHandler routingHander = (StatementHandler)target;
			Field[] fields = routingHander.getClass().getDeclaredFields();
			for (Field field : fields) {
				System.out.println(field.getName());
			}
			Field delegate = routingHander.getClass().getDeclaredField("delegate");
			delegate.setAccessible(true);
			Object obj = delegate.get(target);
			if (obj instanceof PreparedStatementHandler) {
				PreparedStatementHandler handler = (PreparedStatementHandler)obj;
				printPreparedStatementSqlLog(invocation.getArgs()[0],handler);
			}
		}
		return invocation.proceed();
	}
	
	private void printPreparedStatementSqlLog(Object arg,BaseStatementHandler target) throws
			NoSuchFieldException, IllegalAccessException {
		BaseStatementHandler handler = target;
		String sqlTemplete = handler.getBoundSql().getSql();
		//将多个空格和换行符替换为一个
		String reg = "(?m)^\\s*$(\\n|\\r\\n)";
		sqlTemplete = sqlTemplete.replaceAll(reg,"").replaceAll("\t"," ").trim();
		LogEntity logEntity = LogContext.getLogEntity();
		List paramList = logEntity.getParams();
		String sql = injectDataInSql(sqlTemplete,paramList);
		printField(handler);
		System.out.println("======================================================");
		System.out.println("executor method is :\n\t" + logEntity.getInvokeMethod() +"\n");
		System.out.println("executor sql is :\n\t" + sql +"\nsql end");
		System.out.println("======================================================");
		logEntity.setSql(sql);
		LogContext.setLogEntity(logEntity);
	}
	
	private void printField(Object object) {
		Class<?> aClass = object.getClass();
		Field[] declaredFields = aClass.getDeclaredFields();
		for (Field field : declaredFields) {
			System.out.println("field Name:"+field.getName());
		}
	}
	
	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target,this);
	}
	
	private String injectDataInSql(String sql,List<Object> colValues) {
		String[] split = sql.split("\\?");
		StringBuilder sb = new StringBuilder();
		for (int i=0;i < split.length ;i++) {
            if (i < colValues.size()) {
	            sb.append(split[i]);
	            Object obj = colValues.get(i);
	            if (obj instanceof String) {
		            sb.append("'").append(obj).append("'");
	            }else{
		            sb.append(obj);
	            }
            }else{
                sb.append(split[i]);
            }
        }
        return sb.toString();
	}
	
	@Override
	public void setProperties(Properties properties) {
		System.out.println("LogPlugin.setProperties()");
	}

}
