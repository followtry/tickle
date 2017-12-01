/**
 *
 */
package cn.followtry.dubbo.impl;

import cn.followtry.dubbo.api.UserService;
import cn.followtry.dubbo.bean.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * user服务的实现
 *
 * @author jingzz
 * @since 2016年10月26日 上午11:20:28
 */
@Service("userService")
/*
connections:限制最大的客户端连接数为10,
token:true-随机token令牌，使用UUID生成；"xxx123"-固定token令牌，密码为xxx123
 */
@com.alibaba.dubbo.config.annotation.Service(interfaceClass = UserService.class, group = "primary")
@Slf4j
public class UserServiceImpl implements UserService {

  public UserServiceImpl() {
    log.info("cn.followtry.dubbo.impl.UserServiceImpl.UserServiceImpl()");
  }


  @Override
  public User getUserById(String id) {
    User user = new User();
    if (id != null && !"".equals(id)) {
      user.setId(id);
      user.setName("hello world");
      return user;
    }
    user.setName("user is not found");
    return user;
  }

}
