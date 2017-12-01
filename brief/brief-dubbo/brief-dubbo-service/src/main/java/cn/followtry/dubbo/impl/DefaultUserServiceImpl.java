package cn.followtry.dubbo.impl;

import cn.followtry.dubbo.api.UserService;
import cn.followtry.dubbo.bean.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 默认的用户服务. Created by followtry on 2017/4/22 0022.
 */
@Service("defaultUserService")
@Slf4j
public class DefaultUserServiceImpl implements UserService {
  public DefaultUserServiceImpl() {
    log.info("cn.followtry.dubbo.impl.DefaultUserServiceImpl.DefaultUserServiceImpl()");
  }


  @Override
  public User getUserById(String id) {
    return new User() {
      {
        setId(id);
        setName("default-user-1");
      }
    };
  }
}
