package cn.followtry.spring.cloud.followtrycloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer //开启configServer
@SpringBootApplication
//@EnableDiscoveryClient //开启Eureka Client
public class FollowtryCloudApplication {

	public static void main(String[] args) {
		SpringApplication.run(FollowtryCloudApplication.class, args);
	}

}
