/**
 * 
 */
package cn.followtry.mybatis.plugin;

import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;

/**
 * @author jingzz
 * @time 2016年10月19日 下午1:35:52
 * @name brief-mybatis/cn.followtry.mybatis.plugin.ExamplePlugin
 * @since 2016年10月19日 下午1:35:52
 */
@Intercepts({@Signature(type = Executor.class, method ="update", args = {MappedStatement.class, Object.class})})  
public class ExamplePlugin implements Interceptor {

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		System.out.println("ExamplePlugin.intercept()");
		return invocation.proceed();
	}

	@Override
	public Object plugin(Object target) {
		System.out.println("ExamplePlugin.plugin()");
		if (target instanceof StatementHandler) {
			System.out.println("ExamplePlugin.plugin() StatementHandler");
		}else if (target instanceof Executor) {
			System.out.println("ExamplePlugin.plugin() Executor");
		}else if (target instanceof ParameterHandler ) {
			System.out.println("ExamplePlugin.plugin() ParameterHandler");
		}else if (target instanceof ResultSetHandler ) {
			System.out.println("ExamplePlugin.plugin() Executor");
		}
		return target;
	}

	@Override
	public void setProperties(Properties properties) {
		System.out.println("ExamplePlugin.setProperties()");
	}

}
