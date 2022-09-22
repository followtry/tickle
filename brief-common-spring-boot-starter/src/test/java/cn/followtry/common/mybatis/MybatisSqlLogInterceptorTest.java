package cn.followtry.common.mybatis;

import cn.followtry.common.mybatis.mapper.TestMapper;
import cn.followtry.common.mybatis.sqllog.SqlLogInterceptor;
import cn.followtry.common.mybatis.typehandler.LocalDateTimeTypeHandler;
import cn.followtry.common.utils.JsonUtil;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.apache.ibatis.transaction.managed.ManagedTransactionFactory;
import org.junit.Assert;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

/**
 * @author followtry
 * @since 2022/8/8 11:25 上午
 */
public class MybatisSqlLogInterceptorTest {

    private DataSource hikariDataSource() throws Exception {
        HikariConfig configuration = new HikariConfig();
        configuration.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true");
        configuration.setUsername("test");
        configuration.setPassword("test");
        String mysqlDriver = "com.mysql.jdbc.Driver";
        configuration.setDriverClassName(mysqlDriver);
        configuration.setConnectionTimeout(30000);
        configuration.setIdleTimeout(600000);
        configuration.setMaxLifetime(1800000);
        configuration.setPoolName("test-hikari-pool");
        HikariDataSource hikariDataSource = new HikariDataSource(configuration);
        return hikariDataSource;
    }

    public void testHikariDataSourceLog() throws Exception {
        Configuration c = new Configuration();
        c.setEnvironment(new Environment("test-hikari-mybatis", new ManagedTransactionFactory(), hikariDataSource()));
        c.setShrinkWhitespacesInSql(true);
        SqlLogInterceptor interceptor = new SqlLogInterceptor();
        interceptor.setSlowSqlTime(100L);
        c.addInterceptor(interceptor);
        c.getTypeHandlerRegistry().register(new LocalDateTimeTypeHandler());
        c.addMapper(TestMapper.class);
        DefaultSqlSessionFactory sessionFactory = new DefaultSqlSessionFactory(c);
        SqlSession sqlSession = sessionFactory.openSession();
        TestMapper mybatisMapper = c.getMapper(TestMapper.class, sqlSession);
        List<Map<String, Object>> mapList = mybatisMapper.queryBy(502001L);
        System.out.println(JsonUtil.toJson(mapList));
        Assert.assertTrue(mapList.size() > 0);
    }
}
