package cn.followtry.design.pattern.builder;

/**
 * Created by followtry on 2017/6/6.
 */
public class UserCreateDirector {
  
  public static User createUser() {
    AbstractUserBuilder userBuilder = new ConcreteUserBuilder();
    User jingzz = userBuilder.id("123456").idNo("371427199210265842").name("jingzz").pwd
            ("********").sex(User.Sex.Male).create();
    return jingzz;
  }
}
