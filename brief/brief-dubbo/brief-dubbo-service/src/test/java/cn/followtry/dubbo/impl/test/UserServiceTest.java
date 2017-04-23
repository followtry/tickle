package cn.followtry.dubbo.impl.test;

import cn.followtry.dubbo.api.UserService;
import cn.followtry.dubbo.bean.User;
import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.alibaba.dubbo.rpc.Protocol;
import com.alibaba.dubbo.rpc.protocol.dubbo.DubboProtocol;
import java.util.ServiceLoader;
import org.junit.Test;

/**
 * 通过接口，查找实现了接口的类
 * Created by followtry on 2017/4/22 0022.
 */
public class UserServiceTest {

  @Test
  public void test(){
    //传入的接口类必须加上注解com.alibaba.dubbo.common.extension.SPI
    ExtensionLoader<Protocol> loader = ExtensionLoader.getExtensionLoader(Protocol.class);
    Protocol extension = loader.getExtension(DubboProtocol.NAME);
  }

  @Test
  public void getSubClassByInterface() {
    ServiceLoader<UserService> serviceLoader = ServiceLoader.load(UserService.class);



    if (serviceLoader != null) {
      for (UserService userService : serviceLoader) {
        User userById = userService.getUserById("ssssss");
        System.out.println(userById);
      }
    }
  }
}
