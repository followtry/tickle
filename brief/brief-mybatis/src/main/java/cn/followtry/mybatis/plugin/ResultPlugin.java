/**
 * 
 */
package cn.followtry.mybatis.plugin;

import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.SqlSessionFactory;

import java.lang.reflect.Field;
import java.sql.Statement;
import java.util.Collection;
import java.util.Properties;

/**
 * 插件的执行顺序依据其在配置文件中由上到下的顺序，如果想在最先或最后执行插件，就将插件配置在plugins标签的最前或最后
 * @author jingzz
 * @time 2016年10月19日 下午1:35:52
 * @since 2016年10月19日 下午1:35:52
 */
@Intercepts({@Signature(type = ResultSetHandler.class, method ="handleResultSets", args = {Statement.class})})
public class ResultPlugin implements Interceptor {
	
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
		Object proceed = invocation.proceed();
		LogEntity logEntity = LogContext.getLogEntity();
		if (proceed instanceof Collection){
			Integer size = ((Collection)proceed).size();
			logEntity.setResultSize(size);
			System.out.println("======================================================");
			System.out.println("executor method is :\n\t" + logEntity.getInvokeMethod() +"\n");
			System.out.println("executor result size is :\n\t"+logEntity.getResultSize());
			System.out.println("======================================================");
		}
		return proceed;
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
	
	
	@Override
	public void setProperties(Properties properties) {
		System.out.println("com.meituan.hotel.report.plugin.ResultPlugin.setProperties()");
	}

}
