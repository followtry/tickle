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
     * main.
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.apache.calcite.jdbc.Driver");
        Properties info = new Properties();
        info.setProperty("lex", "JAVA");
        Connection connection = DriverManager.getConnection("jdbc:calcite:", info);
        CalciteConnection calciteConnection = connection.unwrap(CalciteConnection.class);
        SchemaPlus rootSchema = calciteConnection.getRootSchema();
        rootSchema.add("hr", new ReflectiveSchema(new JavaHrSchema()));
    
        Statement statement = calciteConnection.createStatement();
        String sql = "select e.emp_id, e.name as emp_name, e.dept_no, d.name as dept_name "
                + "from hr.employee as e "
                + "left join hr.department as d on e.dept_no = d.dept_no";
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
