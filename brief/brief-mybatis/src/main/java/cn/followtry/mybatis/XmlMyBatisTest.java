/**
 * 
 */
package cn.followtry.mybatis;

import cn.followtry.mybatis.bean.User;
import cn.followtry.mybatis.xml.mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 单独操作mybatis持久层框架<br>
 * 以xml配置方式测试mybatis的各种特性
 * @author jingzz
 * @time 2016年10月18日 下午2:24:20
 * @name brief-example/cn.jingzztech.prac.org.mybatis.MainMyBatisTest
 * @since 2016年10月18日 下午2:24:20
 */
public class XmlMyBatisTest {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(XmlMyBatisTest.class);

	
	public static void main(String[] args) throws Exception {
		SqlSession session = MybatisCore.getSession();
		try {
			UserMapper mapper = session.getMapper(UserMapper.class);
			get(mapper);
		}finally {
			session.close();
		}
	}

	/**
	 * @author jingzz
	 */
	private static User get(UserMapper mapper) {
		User user1 = new User();
		user1.setId(12L);
		user1.setName("jjj");
		//获取数据库操作会话
		User user = mapper.getUserById(1L,"jingzhongzhi",user1);
		LOGGER.info(user == null ?"user为空":user.toString());
		return user;
	}
}
