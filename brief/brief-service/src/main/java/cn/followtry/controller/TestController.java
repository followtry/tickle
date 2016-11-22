/**
 * 
 */
package cn.followtry.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.followtry.validation.annotation.NotEmpty;
import cn.followtry.validation.base.common.exception.ErrorCode;
import cn.followtry.validation.base.stereotype.XRestController;
import cn.followtry.validation.base.stereotype.validation.ValidationException;

/**
 * @author jingzz
 * @time 2016年6月2日 下午9:03:56
 * @name brief-service/cn.jingzz.brief.controller.TestController
 * @since 2016年6月2日 下午9:03:56
 */
@XRestController
public class TestController {
	
	
	@RequestMapping(value="/mytest",method=RequestMethod.GET)
	public Object test(@NotEmpty(name = "test") @RequestParam(value="test",required=false)String test){
		throw new RuntimeException("这是运行时错误");
	}
	
	@RequestMapping(value="/mytest2",method=RequestMethod.GET)
	public Object test2(@NotEmpty(name = "test") @RequestParam(value="test",required=false)String test){
		throw new ValidationException(ErrorCode.UNEXPECTED);
	}
}
