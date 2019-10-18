package cn.followtry.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author jingzhongzhi
 */
@SpringBootApplication
@ComponentScan(basePackages = "cn.followtry.boot.java")
public class BriefSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(BriefSpringbootApplication.class, args);
	}

}
