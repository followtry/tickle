package cn.followtry.mybatis.hive;

import cn.followtry.mybatis.hive.mapper.CodeHiveUserMapper;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
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
public class CodeHiveMyBatisTest {

  private static final Logger LOGGER = LoggerFactory.getLogger(CodeHiveMyBatisTest.class);

  private static String url = "jdbc:hive2://h2m1:10000/default";

  private static String driverName = "org.apache.hive.jdbc.HiveDriver";
  private static String username = "";
  private static String password = "";

  private static SqlSessionFactory sessionFactory;

  static {
    // 获取数据源
    DataSource dataSource = new UnpooledDataSource(driverName, url, username, password);

    JdbcTransactionFactory jdbcTF = new JdbcTransactionFactory();

    // 获取环境
    Environment environment = new Environment("development", jdbcTF, dataSource);

    // 构建配置类
    Configuration configuration = new Configuration(environment);
    String packageName = CodeHiveUserMapper.class.getPackage().getName();
    LOGGER.info("package name:{}", packageName);
    // 获取业务接口，逻辑在注解中
    configuration.addMappers(packageName);

    sessionFactory = new SqlSessionFactoryBuilder().build(configuration);
  }

  public static SqlSession getSession() {
    if (sessionFactory == null) {
      return null;
    }
    return sessionFactory.openSession();
  }

  public static void main(String[] args) throws SQLException {
    // 生成sqlsession
    SqlSession session = getSession();

    // 执行业务逻辑
    CodeHiveUserMapper mapper = session.getMapper(CodeHiveUserMapper.class);
    getDBList(mapper);

    getTableList(mapper);
  }

  /**
   * @author jingzz
   */
  private static void getDBList(CodeHiveUserMapper mapper) {
    List<String> dbList = mapper.getDBList();
    LOGGER.info(dbList.toString());
  }

  private static void getTableList(CodeHiveUserMapper mapper) {
    List<String> tablesName = mapper.getTablelist();
    LOGGER.info(tablesName.toString());
  }
}
