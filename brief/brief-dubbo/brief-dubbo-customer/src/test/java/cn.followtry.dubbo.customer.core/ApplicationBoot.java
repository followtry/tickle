package cn.followtry.dubbo.customer.core;

import cn.followtry.dubbo.bean.User;
import cn.followtry.dubbo.customer.ServiceHandler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * .
 *
 * @author jingzz
 * @since 2016年10月26日 下午4:16:03
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:applicationContext.xml")
public class ApplicationBoot {

  @Autowired
  private ServiceHandler serviceHandler;

  @Test
  public void testUser() {
    User jingzz = serviceHandler.getUser("zhangsan");
    System.out.println(jingzz);

    User zhangsan2 = serviceHandler.getUser2("zhangsan2");
    System.out.println(zhangsan2);
  }

}
