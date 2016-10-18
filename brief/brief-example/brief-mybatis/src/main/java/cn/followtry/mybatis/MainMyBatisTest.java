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

import cn.followtry.mybatis.bean.User;

/**
 * @author jingzz
 * @time 2016年10月18日 下午2:24:20
 * @name brief-example/cn.jingzztech.prac.org.mybatis.MainMyBatisTest
 * @since 2016年10月18日 下午2:24:20
 */
public class MainMyBatisTest {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MainMyBatisTest.class);

	private static SqlSessionFactory sessionFactory;

	static {
		try {
			Reader resourceAsReader = Resources.getResourceAsReader("mybatis-config.xml");
			//读取配置，构造session工厂
			sessionFactory = new SqlSessionFactoryBuilder().build(resourceAsReader);
		} catch (IOException e) {
			LOGGER.error("构建资源报错",e);
		}
	}

	public static void main(String[] args) {
		//获取数据库操作会话
		SqlSession session = sessionFactory.openSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		User user = mapper.getUserById(1L);
		LOGGER.info(user.toString());
	}
}
