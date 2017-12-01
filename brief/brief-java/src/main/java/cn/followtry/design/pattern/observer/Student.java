package cn.followtry.design.pattern.observer;

/**
 * 学生订阅者，可以从老师那获得上课和下课等指令
 * Created by followtry on 2017/6/5.
 */
public class Student extends AbstractObServer {
  
  public Student(String name,Topic topic) {
    super(name,topic);
  }
  

  @Override
  public void accepet(String msg) {
    System.out.println(super.getName()+"同学，"+msg);
  }
}
