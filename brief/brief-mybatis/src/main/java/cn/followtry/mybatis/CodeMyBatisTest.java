package cn.followtry.mybatis;

import cn.followtry.mybatis.bean.User;
import cn.followtry.mybatis.code.mapper.CodeUserMapper;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.SQLException;

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
    String driver = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://127.0.0.1:3306/brief?characterEncoding=utf-8";
    String username = "brief";
    String password = "brief";
    //获取数据源
    DataSource dataSource = new UnpooledDataSource(driver,url,username,password);
    JdbcTransactionFactory jdbcTF = new JdbcTransactionFactory();

    //获取环境
    Environment environment = new Environment("development",jdbcTF,dataSource);

    //构建配置类
    Configuration configuration = new Configuration(environment);

    //获取业务接口，逻辑在注解中
    configuration.addMapper(CodeUserMapper.class);
    configuration.addMappers("cn.followtry");

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
