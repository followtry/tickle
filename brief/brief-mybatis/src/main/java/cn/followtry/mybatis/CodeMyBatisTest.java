package cn.followtry.mybatis;

import cn.followtry.mybatis.bean.User;
import cn.followtry.mybatis.code.mapper.CodeUserMapper;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 单独操作mybatis持久层框架.
 *
 * <p>以编写java代码方式测试mybatis的各种特性
 *
 * @author jingzz
 * @since 2016年10月19日 上午10:13:03
 */
public class CodeMyBatisTest {

  private static final Logger LOGGER = LoggerFactory.getLogger(CodeMyBatisTest.class);

  /**
   * main.
   */
  public static void main(String[] args) throws SQLException {
    String driver = "com.mysql.jdbc.Driver";
    String url = "jdbc:mysql://172.20.19.200:3306/worktime?characterEncoding=utf-8";
    String username = "worktime";
    String password = "worktime";
    //获取数据源
    DataSource dataSource = new UnpooledDataSource(driver,url,username,password);
    JdbcTransactionFactory jdbcTF = new JdbcTransactionFactory();

    //获取环境
    Environment environment = new Environment("development",jdbcTF,dataSource);

    //构建配置类
    Configuration configuration = new Configuration(environment);

    //获取业务接口，逻辑在注解中
    configuration.addMapper(CodeUserMapper.class);

    //生成sqlsession
    SqlSession session = new SqlSessionFactoryBuilder().build(configuration).openSession();

    //执行业务逻辑
    CodeUserMapper mapper = session.getMapper(CodeUserMapper.class);
    get(mapper);
  }

  /**
   * @author jingzz
   */
  private static User get(CodeUserMapper mapper) {
    //获取数据库操作会话
    User user = mapper.getUserById(1L);
    LOGGER.info(user.toString());
    return user;
  }
}
