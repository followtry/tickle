package cn.followtry.dubbo.impl;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * dubbo 服务提供者启动类（调试程序用，不需要使用tomcat等servlet容器启动）
 * Created by followtry on 2017/5/7 0007.
 */
public class DubboProvider {

  private static final Logger LOGGER = LoggerFactory.getLogger(DubboProvider.class);

  /** main. */
  public static void main(String[] args) {
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext
            ("classpath*:**/applicationContext.xml");
    context.start();

    synchronized (DubboProvider.class) {
      while (true) {
        try {
          DubboProvider.class.wait();
          LOGGER.info("Dubbo provider is waiting...");
        } catch (InterruptedException e) {
          LOGGER.error("synchronized",e);
        }
      }
    }
  }
}
