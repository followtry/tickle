/**
 * 
 */
package cn.followtry.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.followtry.springboot.springinaction.aop.Performance;

/**
 * @author jingzz
 * @time 2016年10月13日 上午9:09:48
 * @name brief-springboot/cn.followtry.springboot.controller.TestController
 * @since 2016年10月13日 上午9:09:48
 */
@RestController
public class TestController {
	
	@Autowired
	private Performance performance;
	
	@RequestMapping("/test")
	public void test(@RequestParam("name")String name){
		System.out.println("TestController.test()");
		performance.perform();
		System.out.println();
		System.out.println();
		System.out.println("TestController.test()2");
		performance.perform(name);
		System.out.println("TestController.test()3");
	}
}
