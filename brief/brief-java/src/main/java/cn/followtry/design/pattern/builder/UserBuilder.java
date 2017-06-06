package cn.followtry.design.pattern.builder;

/**
 * Created by followtry on 2017/6/6.
 */
public abstract class UserBuilder {
  
  abstract UserBuilder id(String id);
  abstract UserBuilder name(String name);
  abstract UserBuilder pwd(String pwd);
  abstract UserBuilder sex(User.Sex sex);
  abstract UserBuilder IdNo(String IdNo);
  
  abstract User create();

}
