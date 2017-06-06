package cn.followtry.design.pattern.builder;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by followtry on 2017/6/6.
 */
@Setter
@Getter
@ToString
public class User {
  private String name;
  
  private String id;
  
  private String pwd;
  
  private Sex sex;
  
  private String IdNo;
  
  enum Sex{
    Male,Female;
  }
}
