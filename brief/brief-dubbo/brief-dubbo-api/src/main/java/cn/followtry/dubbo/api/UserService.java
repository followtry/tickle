package cn.followtry.dubbo.api;

import cn.followtry.dubbo.bean.User;

/**
 * @author jingzz
 * @since 2016年10月26日 上午11:12:38
 */
public interface UserService {

  User getUserById(String id);
}
