package cn.followtry.design.pattern.builder;

/**
 * Created by followtry on 2017/6/6.
 */
public class User {
  private String name;
  
  private String id;
  
  private String pwd;
  
  private Sex sex;
  
  private String IdNo;
  
  enum Sex{
    Male,Female;
  }
  
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public String getId() {
    return id;
  }
  
  public void setId(String id) {
    this.id = id;
  }
  
  public String getPwd() {
    return pwd;
  }
  
  public void setPwd(String pwd) {
    this.pwd = pwd;
  }
  
  public Sex getSex() {
    return sex;
  }
  
  public void setSex(Sex sex) {
    this.sex = sex;
  }
  
  public String getIdNo() {
    return IdNo;
  }
  
  public void setIdNo(String idNo) {
    IdNo = idNo;
  }
  
  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("User{");
    sb.append("name='").append(name).append('\'');
    sb.append(", id='").append(id).append('\'');
    sb.append(", pwd='").append(pwd).append('\'');
    sb.append(", sex=").append(sex);
    sb.append(", IdNo='").append(IdNo).append('\'');
    sb.append('}');
    return sb.toString();
  }
}
