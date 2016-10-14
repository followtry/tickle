/**
 * 
 */
package cn.followtry.springboot.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
public class HelloController {
	
	@Autowired
	private Performance performance;
	
	@RequestMapping(value="/test")
	public void test(@RequestParam("name")String name){
		System.out.println("HelloController.test()");
		performance.perform();
		System.out.println();
		System.out.println();
		System.out.println("HelloController.test()2");
		performance.perform(name);
		System.out.println("HelloController.test()3");
	}
	@RequestMapping(value={"/","/index"},produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Object rootHandler(){
		Map<Object, Object> res = new HashMap<Object,Object>();
		res.put("data", "this is my app end-point 哈哈");
		return res;
	}
}
