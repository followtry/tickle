package cn.followtry.design.pattern.builder;

/**
 * Created by followtry on 2017/6/6.
 */
public abstract class AbstractUserBuilder {
  
  abstract AbstractUserBuilder id(String id);
  abstract AbstractUserBuilder name(String name);
  abstract AbstractUserBuilder pwd(String pwd);
  abstract AbstractUserBuilder sex(User.Sex sex);
  abstract AbstractUserBuilder idNo(String idNo);
  
  abstract User create();

}
