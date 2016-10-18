/**
 * 
 */
package cn.followtry.springboot.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
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
	
	//处理不存在的URL
	@RequestMapping(value={"/**",})
	public ResponseEntity<Object> root(HttpServletRequest request,HttpServletResponse response){
		Map<Object, Object> res = new HashMap<Object,Object>();
		res.put("message", "未找到请求的资源");
		res.put("URL", request.getServletPath());
		res.put("timestamp", System.currentTimeMillis()/1000);
		res.put("status", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Object>(res, HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value="/test")
	public void test(@RequestParam(value="name",required=false,defaultValue="荆中志")@Valid @Size(min=6) String name){
		System.out.println("HelloController.test()");
		performance.perform();
		System.out.println();
		System.out.println();
		System.out.println("HelloController.test()2");
		performance.perform(name);
		System.out.println("HelloController.test()3");
	}
	
	//根据不同的参数进行不同的请求
	@RequestMapping(value={"/","/index"},params={"name"},consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Object rootHandler(){
		Map<Object, Object> res = new HashMap<Object,Object>();
		res.put("data", "this is my app end-point 哈哈1");
		return res;
	}
	
	@RequestMapping(value={"/","/index"},produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Object rootHandler2(){
		Map<Object, Object> res = new HashMap<Object,Object>();
		res.put("data", "this is my app end-point 哈哈2");
		return res;
	}
	
	@RequestMapping(value={"/model"},produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Object modelHamder(Model model){
		model.addAttribute("name", "jingzz");
		Map<Object, Object> res = new HashMap<Object,Object>();
		res.put("data", model);
		return res;
	}
}
