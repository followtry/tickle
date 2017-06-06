package cn.followtry.design.pattern.builder;

/**
 * Created by followtry on 2017/6/6.
 */
public class ConcreteUserBuilder extends UserBuilder {
  
  User user = new User();
  
  
  @Override
  UserBuilder id(String id) {
    user.setId(id);
    return this;
  }
  
  @Override
  UserBuilder name(String name) {
    user.setName(name);
    return this;
  }
  
  @Override
  UserBuilder pwd(String pwd) {
    user.setPwd(pwd);
    return this;
  }
  
  @Override
  UserBuilder sex(User.Sex sex) {
    user.setSex(sex);
    return this;
  }
  
  @Override
  UserBuilder IdNo(String IdNo) {
    user.setIdNo(IdNo);
    return this;
  }
  
  @Override
  User create() {
    return user;
  }
}
