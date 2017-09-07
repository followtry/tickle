package cn.followtry.design.pattern.builder;

/**
 * Created by followtry on 2017/6/6.
 */
public class ConcreteUserBuilder extends UserBuilder {
  
  private User user = new User();
  
  
  @Override
  public UserBuilder id(String id) {
    user.setId(id);
    return this;
  }
  
  @Override
  public UserBuilder name(String name) {
    user.setName(name);
    return this;
  }
  
  @Override
  public UserBuilder pwd(String pwd) {
    user.setPwd(pwd);
    return this;
  }
  
  @Override
  public UserBuilder sex(User.Sex sex) {
    user.setSex(sex);
    return this;
  }
  
  @Override
  public UserBuilder IdNo(String IdNo) {
    user.setIdNo(IdNo);
    return this;
  }
  
  @Override
  public User create() {
    return user;
  }
}
