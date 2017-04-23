package cn.followtry.dubbo.api;

import cn.followtry.dubbo.bean.User;
import java.io.Serializable;

/**
 * @author jingzz
 * @since 2016年10月26日 上午11:12:38
 */
public interface UserService extends Serializable {

  User getUserById(String id);
}
