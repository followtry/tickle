import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.lang3.builder.ToStringBuilder;

import static jdk.nashorn.internal.objects.Global.print;

public class Test {

  class Student {
    String stuName;
    int age;
    String country;

    public String getStuName() {
      return stuName;
    }

    public void setStuName(String stuName) {
      this.stuName = stuName;
    }

    public int getAge() {
      return age;
    }

    public void setAge(int age) {
      this.age = age;
    }

    public String getCountry() {
      return country;
    }

    public void setCountry(String country) {
      this.country = country;
    }

  }


  public static void main(String[] args) {
    Test t = new Test();
    t.test();
  }

  private void test() {
    //补全代码
    List<Student> students = init();
    students.stream().forEach(student -> System.out.println(student));
  }

  private List<Student> init() {
    List<Student> students = new ArrayList<>();
    Student stu = new Student();
    stu.setStuName("stu1");
    stu.setAge(22);
    stu.setCountry("China");
    students.add(stu);
    stu = new Student();
    stu.setStuName("stu2");
    stu.setAge(25);
    stu.setCountry("USA");
    students.add(stu);
    stu = new Student();
    stu.setStuName("stu3");
    stu.setAge(23);
    stu.setCountry("China");
    students.add(stu);
    return students;
  }

}
