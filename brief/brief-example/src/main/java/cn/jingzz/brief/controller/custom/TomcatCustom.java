/**
 * 
 */
package cn.jingzz.brief.controller.custom;

import org.apache.catalina.startup.Tomcat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author jingzz
 * @time 2016年7月26日 下午3:40:07
 * @name brief-layer/cn.jingzz.brief.controller.custom.TomcatCustom
 * @since 2016年7月26日 下午3:40:07
 */
@ComponentScan(basePackages="cn.jingzz.brief")
@SpringBootApplication
public class TomcatCustom implements EmbeddedServletContainerCustomizer{
		
	private static final Logger LOG = LoggerFactory.getLogger(TomcatCustom.class);
	
	@Override
	public void customize(ConfigurableEmbeddedServletContainer container) {
//		container.setPort(8090);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(TomcatCustom.class, args);
		LOG.debug("jing1");
		LOG.info("jing2");
		LOG.warn("jing3");
		LOG.error("jing4");
	}
}
