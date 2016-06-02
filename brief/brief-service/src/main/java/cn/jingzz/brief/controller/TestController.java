/**
 * 
 */
package cn.jingzz.brief.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jingzz.brief.service.TestService;

/**
 * @author jingzz
 * @time 2016年6月2日 下午9:03:56
 * @name brief-service/cn.jingzz.brief.controller.TestController
 * @since 2016年6月2日 下午9:03:56
 */
@Controller
public class TestController {
	
	@Autowired
	private TestService testService;
	
	@RequestMapping("/test")
	@ResponseBody
	public List<String> test(){
		testService.test();
		return Arrays.asList("success");
	}
}
