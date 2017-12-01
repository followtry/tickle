package cn.followtry.design.pattern.builder;

/**
 * Created by followtry on 2017/6/6.
 */
public class ConcreteUserBuilder extends AbstractUserBuilder {
  
  User user = new User();
  
  
  @Override
  AbstractUserBuilder id(String id) {
    user.setId(id);
    return this;
  }
  
  @Override
  AbstractUserBuilder name(String name) {
    user.setName(name);
    return this;
  }
  
  @Override
  AbstractUserBuilder pwd(String pwd) {
    user.setPwd(pwd);
    return this;
  }
  
  @Override
  AbstractUserBuilder sex(User.Sex sex) {
    user.setSex(sex);
    return this;
  }
  
  @Override
  AbstractUserBuilder idNo(String idNo) {
    user.setIdNo(idNo);
    return this;
  }
  
  @Override
  User create() {
    return user;
  }
}
