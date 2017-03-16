/**
 * 
 */
package cn.followtry.controller;

import cn.followtry.service.IHello;
import cn.followtry.validation.annotation.NotEmpty;
import cn.followtry.validation.base.stereotype.XController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jingzz
 * @time 2016年6月2日 下午9:03:56
 * @name brief-service/cn.jingzz.brief.controller.TestController
 * @since 2016年6月2日 下午9:03:56
 */
@RestController
@XController
public class TestController {
	
	@Autowired
	private IHello helloService;

	@RequestMapping(value="/mytest",method=RequestMethod.GET)
	public Object test(@NotEmpty(name = "content") @RequestParam(value="content",required=false)String content){
		String sayHello = helloService.sayHello(content);

		return sayHello;
	}
}
