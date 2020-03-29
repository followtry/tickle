package cn.followtry.incubate.calcite;

/**
 * 参考自
 * <a>https://www.jianshu.com/p/4f4fea8abfab</a>
 * @author jingzhongzhi
 * @since 2020/3/29
 */
public class JavaHrSchema {
    public static class Employee {
        public final int emp_id;
        public final String name;
        public final int dept_no;
        
        public Employee(int emp_id, String name, int dept_no) {
            this.emp_id = emp_id;
            this.name = name;
            this.dept_no = dept_no;
        }
    }
    
    public static class Department {
        public final String name;
        public final int dept_no;
        
        public Department(int dept_no, String name) {
            this.dept_no = dept_no;
            this.name = name;
        }
    }
    
    public final Employee[] employee = {
            new Employee(100, "joe", 1),
            new Employee(200, "oliver", 2),
            new Employee(300, "twist", 1),
            new Employee(301, "king", 3),
            new Employee(305, "kelly", 1)
    };
    
    public final Department[] department = {
            new Department(1, "dev"),
            new Department(2, "market"),
            new Department(3, "test")
    };
}
