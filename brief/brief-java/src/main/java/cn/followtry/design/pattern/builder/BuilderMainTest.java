package cn.followtry.design.pattern.builder;

/**
 * Created by followtry on 2017/6/6.
 */
public class BuilderMainTest {
  /** main. */
  public static void main(String[] args) {
    User user = UserCreateDirector.createUser();
    System.out.println(user);
    
    
    
  }
}
