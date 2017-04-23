
package cn.followtry.dubbo.bean;

import java.io.Serializable;
import lombok.Data;
import lombok.ToString;

/**
 * @author jingzz
 * @since 2016年10月26日 上午11:12:57
 */
@Data
@ToString
public class User implements Serializable {

  /**  */
  private static final long serialVersionUID = 3899555909604815507L;

  private String name;

  private String id;
}
