/**
 *
 */
package cn.followtry.dubbo.bean;

import java.io.Serializable;

/**
 * @author jingzz
 * @since 2016年10月26日 上午11:12:57
 */
public class User implements Serializable {

  /**  */
  private static final long serialVersionUID = 3899555909604815507L;

  private String name;

  private String id;

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

  @Override
  public String toString() {
    return "User [name=" + name + ", id=" + id + "]";
  }
}
