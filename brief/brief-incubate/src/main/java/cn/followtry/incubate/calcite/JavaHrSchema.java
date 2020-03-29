package cn.followtry.incubate.calcite;

/**
 * 参考自
 * <a>https://www.jianshu.com/p/4f4fea8abfab</a>
 * @author jingzhongzhi
 * @since 2020/3/29
 */
public class JavaHrSchema {
    public static class Employee {
        public final int empId;
        public final String name;
        public final int deptNo;
        
        public Employee(int empId, String name, int deptNo) {
            this.empId = empId;
            this.name = name;
            this.deptNo = deptNo;
        }
    }
    
    public static class Department {
        public final String name;
        public final int deptNo;
        
        public Department(int deptNo, String name) {
            this.deptNo = deptNo;
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
