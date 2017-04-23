package cn.followtry.dubbo.impl;

import cn.followtry.dubbo.api.UserService;
import cn.followtry.dubbo.bean.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 自定义用户服务. Created by followtry on 2017/4/22 0022.
 */
@Service
@com.alibaba.dubbo.config.annotation.Service(interfaceClass = UserService.class,group = "custom")
@Slf4j
public class CustomUserServiceImpl implements UserService {
  public CustomUserServiceImpl() {
    log.info("cn.followtry.dubbo.api.UserService.CustomUserServiceImpl.CustomUserServiceImpl()");
  }

  @Override
  public User getUserById(String id) {
    return new User() {

      /*
       该部分操作相当于匿名集成了类User,但是返回的是父类User,因此在匿名继承类中覆盖父类的name并为其赋值在外部是访问不到的.
       而且在该类中自定义属性也无法在外部调用。但是可以像匿名实现接口一样实现实现内部的方法
       */

      {
        setId(id);
        setName("custom-user-1");
      }
    };
  }
}
