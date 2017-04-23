package cn.followtry.dubbo.customer;

import cn.followtry.dubbo.api.UserService;
import cn.followtry.dubbo.bean.User;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

/**
 * .
 *
 * @author jingzz
 * @since 2016年10月26日 上午11:17:47
 */
@Service
public class ServiceHandler {

  //  @Autowired
  //  @Qualifier("userService")
  @Reference(interfaceClass = UserService.class, group = "primary")
  private UserService userService;

  //非注解方式，Spring bean方式
  //  @Autowired
  //  @Qualifier("customUserService")


  /*
   注解方式而非配置方式引用远程的接口 ，因为该方式是通过注解引用的没有指定id值，因此不能使用@Qualifier("userService")方式指定
   url参数用于消费方直连服务提供方的开发阶段
    */
  public User getUser(String id) {
    return userService.getUserById(id);
  }

  //  @Autowired
  @Reference(interfaceClass = UserService.class,group = "custom")
  private UserService customUserService;



  public User getUser2(String id) {
    return customUserService.getUserById(id);
  }
}
