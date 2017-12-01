/**
 * 
 */
package cn.followtry.mybatis.plugin;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import org.apache.ibatis.binding.BindingException;
import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.TypeHandlerRegistry;

/**
 * 插件的执行顺序依据其在配置文件中由上到下的顺序，如果想在最先或最后执行插件，就将插件配置在plugins标签的最前或最后
 * @author jingzz
 * @time 2016年10月19日 下午1:35:52
 * @since 2016年10月19日 下午1:35:52
 */
@Intercepts({@Signature(type = ParameterHandler.class, method ="setParameters", args = {PreparedStatement.class})})
public class ParamsPlugin implements Interceptor {
	
	private SqlSessionFactory sqlSessionFactory;
	
	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}
	
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	

    @Override
	public Object intercept(Invocation invocation) throws Throwable {
		System.out.println(invocation.getTarget());
		setParameters((ParameterHandler)invocation.getTarget());
		return invocation.proceed();
	}
	
	private Field getField(ParameterHandler target,String filedName) throws NoSuchFieldException {
		
		Field declaredField = target.getClass().getDeclaredField(filedName);
		declaredField.setAccessible(true);
		return declaredField;
	}
	
	public void setParameters(ParameterHandler parameterHandler) throws NoSuchFieldException,
			IllegalAccessException {
		LogEntity logEntity = new LogEntity();
		Field mappedStatement1 = getField(parameterHandler,"mappedStatement");
		MappedStatement mappedStatement = (MappedStatement)mappedStatement1.get(parameterHandler);
		logEntity.setInvokeMethod(mappedStatement.getId());
		Field boundSql1 = getField(parameterHandler,"boundSql");
		BoundSql boundSql = (BoundSql)boundSql1.get(parameterHandler);
		Field parameterObject1 = getField(parameterHandler,"parameterObject");
		Object parameterObject = parameterObject1.get(parameterHandler);
		Field typeHandlerRegistry1 = getField(parameterHandler,"typeHandlerRegistry");
		TypeHandlerRegistry typeHandlerRegistry = (TypeHandlerRegistry)typeHandlerRegistry1.get(parameterHandler);
		
		
		Field configuration1 = getField(parameterHandler,"configuration");
		Configuration configuration = (Configuration)configuration1.get(parameterHandler);
		ErrorContext.instance().activity("setting parameters").object(mappedStatement.getParameterMap().getId());
		List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
		if (parameterMappings != null) {
			List<Object> paramValues = new ArrayList<>();
			for (int i = 0; i < parameterMappings.size(); i++) {
				ParameterMapping parameterMapping = parameterMappings.get(i);
				if (parameterMapping.getMode() != ParameterMode.OUT) {
					Object value;
					String propertyName = parameterMapping.getProperty();
					if (boundSql.hasAdditionalParameter(propertyName)) { // issue #448 ask first for additional params
						value = boundSql.getAdditionalParameter(propertyName);
					} else if (parameterObject == null) {
						value = null;
					} else if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
						value = parameterObject;
					} else {
						MetaObject metaObject = configuration.newMetaObject(parameterObject);
						value = metaObject.getValue(propertyName);
					}
					paramValues.add(value);
				}
				
			}
			logEntity.setParams(paramValues);
			LogContext.setLogEntity(logEntity);
			System.out.println(logEntity);
		}
	}
	
	

    @Override
	public Object plugin(Object target) {
		return Plugin.wrap(target,this);
	}
	
	private void injectDataInSql(String sql,List<Object> colValues) {
		String[] split = sql.split("\\?");
		StringBuilder sb = new StringBuilder();
		for (int i=0;i < split.length ;i++) {
            if (i < colValues.size()) {
                sb.append(split[i]).append(colValues.get(i));
            }else{
                sb.append(split[i]);
            }
        }
	}
	

    @Override
	public void setProperties(Properties properties) {
		System.out.println("LogPlugin.setProperties()");
	}
	
	private Object wrapCollection(final Object object) {
		if (object instanceof Collection) {
			StrictMap<Object> map = new StrictMap<Object>();
			map.put("collection", object);
			if (object instanceof List) {
				map.put("list", object);
			}
			return map;
		} else if (object != null && object.getClass().isArray()) {
			StrictMap<Object> map = new StrictMap<Object>();
			map.put("array", object);
			return map;
		}
		return object;
	}
	
	public static class StrictMap<V> extends HashMap<String, V> {
		
		private static final long serialVersionUID = -5741767162221585340L;
		
		@Override
		public V get(Object key) {
			if (!super.containsKey(key)) {
				throw new BindingException("Parameter '" + key + "' not found. Available parameters are " + this.keySet());
			}
			return super.get(key);
		}
		
	}

}
