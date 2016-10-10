/**
 * 
 */
package cn.jingzz.brief.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yonyou.tools.validation.annotation.NotNull;
import com.yonyou.tools.validation.base.stereotype.XController;

/**
 * @author jingzz
 * @time 2016年6月2日 下午9:03:56
 * @name brief-service/cn.jingzz.brief.controller.TestController
 * @since 2016年6月2日 下午9:03:56
 */
@Controller
@XController
public class TestController {
	
	
	@RequestMapping(value="/mytest",method=RequestMethod.GET)
	@ResponseBody
	public Object test(@NotNull(name = "test") @RequestParam(value="test",required=false)String test){
		return test;
	}
}
