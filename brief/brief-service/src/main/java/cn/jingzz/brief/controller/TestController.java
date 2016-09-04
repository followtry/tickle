/**
 * 
 */
package cn.jingzz.brief.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author jingzz
 * @time 2016年6月2日 下午9:03:56
 * @name brief-service/cn.jingzz.brief.controller.TestController
 * @since 2016年6月2日 下午9:03:56
 */
@Controller
public class TestController {
	
	
	@RequestMapping(value="/test",method=RequestMethod.GET)
	@ResponseBody
	public Object test(HttpServletRequest request,HttpServletResponse response){
		return new Object();
	}
}
