package cn.followtry.incubate.calcite;

import org.apache.calcite.jdbc.CalciteConnection;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.FileSystemResource;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * calcite 连接 mysql <a>https://blog.csdn.net/hjw199089/article/details/82717377</a>
 * 1. calcite 的连接属性设置。 来源于{@link org.apache.calcite.config.CalciteConnectionProperty}，如 info.setProperty("lex", "JAVA");即将词法设置为 java
 *
 * @author jingzhongzhi
 * @since 2020/3/29
 */
public class QueryMySql {
    
    public static final String basePath = "/Users/jingzhongzhi/git-project/tickle/brief/brief-incubate/src/main/resources";
    
    /**
     * 1. calcite 的连接属性设置。 来源于{@link org.apache.calcite.config.CalciteConnectionProperty}，如 info.setProperty("lex", "JAVA");即将词法设置为 java
     * main.
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
        Class.forName("org.apache.calcite.jdbc.Driver");
        Properties info = new Properties();
        //连接词法为Mysql
        info.setProperty("lex", "MYSQL");
        File modelFile = new FileSystemResource(basePath + "/calcite/model.json").getFile();;
        String modelJson = FileUtils.readFileToString(modelFile, StandardCharsets.UTF_8);
        //连接 Mysql 的模型
        info.setProperty("model", "inline:" + modelJson);
        //连接的 url，通过判断前缀是否为jdbc:calcite: 或  jdbc:avatica:remote:来判断是否使用 calcite
        String connUrl = "jdbc:calcite:";
        Connection connection = DriverManager.getConnection(connUrl, info);
        CalciteConnection calciteConnection = connection.unwrap(CalciteConnection.class);
        calciteConnection.getRootSchema().setCacheEnabled(true);
        ResultSet tables = calciteConnection.getMetaData().getTables(null, null, null, null);
        printResult(tables);
        Statement statement = calciteConnection.createStatement();
    
        File sqlFile = new FileSystemResource(basePath + "/calcite/mysql.sql").getFile();;
        String sql = FileUtils.readFileToString(sqlFile, StandardCharsets.UTF_8);
        ResultSet resultSet = statement.executeQuery(sql);
        printResult(resultSet);
        
        resultSet.close();
        statement.close();
        connection.close();
        
    }
    
    private static void printResult(ResultSet resultSet) throws SQLException {
        /**
         * 遍历 SQL 执行结果
         */
        while (resultSet.next()) {
            for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                System.out.print(resultSet.getMetaData().getColumnName(i) + ":" + resultSet.getObject(i));
                System.out.print(" | ");
            }
            System.out.println();
        }
    }
}
