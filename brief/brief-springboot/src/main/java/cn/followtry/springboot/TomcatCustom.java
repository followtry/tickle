package cn.followtry.springboot;

import cn.followtry.validation.http.MonitorServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * .
 *
 * @author jingzz
 * @since 2016年7月26日 下午3:40:07
 */
@ComponentScan(basePackageClasses = { TomcatCustom.class }, basePackages = { "cn.followtry" })
@SpringBootApplication
@ServletComponentScan(basePackageClasses = MonitorServlet.class)
public class TomcatCustom implements EmbeddedServletContainerCustomizer {

  private static final Logger LOG = LoggerFactory.getLogger(TomcatCustom.class);

  /**
   * custom.
   */
  @Override
  public void customize(ConfigurableEmbeddedServletContainer container) {
    container.setPort(8080);
  }

  /**
   * main.
   */
  public static void main(String[] args) {
    SpringApplication.run(TomcatCustom.class, args);
    LOG.debug("jing1");
    LOG.info("jing2");
    LOG.warn("jing3");
    LOG.error("jing4");
  }
}
