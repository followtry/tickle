package cn.followtry.incubate.calcite;

import org.apache.calcite.adapter.java.ReflectiveSchema;
import org.apache.calcite.jdbc.CalciteConnection;
import org.apache.calcite.schema.SchemaPlus;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author jingzhongzhi
 * @since 2020/3/29
 */
public class QueryDemo {
    /**
     *
     * 1. calcite 的连接属性设置。 来源于{@link org.apache.calcite.config.CalciteConnectionProperty}，如 info.setProperty("lex", "JAVA");即将词法设置为 java
     * main.
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.apache.calcite.jdbc.Driver");
        Properties info = new Properties();
        //连接属性设置
        info.setProperty("lex", "JAVA");
        //连接的 url，通过判断前缀是否为jdbc:calcite: 或  jdbc:avatica:remote:来判断是否使用 calcite
        String connUrl = "jdbc:calcite:";
        Connection connection = DriverManager.getConnection(connUrl, info);
        CalciteConnection calciteConnection = connection.unwrap(CalciteConnection.class);
        SchemaPlus rootSchema = calciteConnection.getRootSchema();
        rootSchema.add("hr", new ReflectiveSchema(new JavaHrSchema()));
    
        Statement statement = calciteConnection.createStatement();
        String sql = "select e.empId, e.name as emp_name, e.deptNo, d.name as dept_name " +
                "from hr.employee as e " +
                "left join hr.department as d on e.deptNo = d.deptNo " +
                "where d.deptNo = 1";
        ResultSet resultSet = statement.executeQuery(sql);
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
    
        resultSet.close();
        statement.close();
        connection.close();
    
    }
}
