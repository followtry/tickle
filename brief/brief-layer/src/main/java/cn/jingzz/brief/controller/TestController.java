/**
 * 
 */
package cn.jingzz.brief.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jingzz
 * @time 2016年7月26日 下午3:14:26
 * @name brief-layer/cn.jingzz.brief.controller.TestController
 * @since 2016年7月26日 下午3:14:26
 */
@RestController
@SpringBootApplication
public class TestController {
	
	@RequestMapping("/test")
	public String test(){
		return "hello world";
	}
}
