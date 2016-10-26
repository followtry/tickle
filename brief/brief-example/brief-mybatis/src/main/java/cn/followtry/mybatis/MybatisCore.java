/**
 * 
 */
package cn.followtry.mybatis;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 构造单例的SqlSessionFactory
 * @author jingzz
 * @time 2016年10月19日 上午9:47:29
 * @name brief-mybatis/cn.followtry.mybatis.MybatisCore
 * @since 2016年10月19日 上午9:47:29
 */
public class MybatisCore {
	
	private MybatisCore(){}
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MybatisCore.class);

	private static SqlSessionFactory sessionFactory;

	static {
		try {
 			Reader resourceAsReader = Resources.getResourceAsReader("cn/followtry/mybatis/mybatis-config.xml");
			//读取配置，构造session工厂
			sessionFactory = new SqlSessionFactoryBuilder().build(resourceAsReader);
		} catch (IOException e) {
			LOGGER.error("构建资源报错",e);
		}
	}

	public static SqlSessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public static SqlSession getSession() {
		return sessionFactory.openSession();
	}
	
	
}
