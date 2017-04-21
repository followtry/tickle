package cn.followtry.springboot.springinaction.aop;

import org.springframework.stereotype.Service;

/**.
 * @author jingzz
 * @since 2016年10月13日 上午9:13:11
 */
@Service
public class PerformanceImpl implements Performance {

  @Override
  public void perform() {
    System.out.println("PerformanceImpl.perform()");
  }

  @Override
  public void perform(String name) {
    System.out.println("PerformanceImpl.perform() name:" + name);
  }

}
