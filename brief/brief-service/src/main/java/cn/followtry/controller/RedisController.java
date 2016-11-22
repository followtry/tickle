/**
 * 
 */
package cn.followtry.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.followtry.service.RedisService;

/**
 * @author jingzz
 * @time 2016年6月2日 下午9:03:56
 * @name brief-service/cn.jingzz.brief.controller.TestController
 * @since 2016年6月2日 下午9:03:56
 */
@Controller
public class RedisController {
	
	@Autowired
	private RedisService redisService;
	
	@RequestMapping(value="/redis/{key}/{value}",method=RequestMethod.GET)
	@ResponseBody
	public String setRedis(@PathVariable("key")String key,
			@PathVariable("value")String value){
		redisService.set(key, value);
		return "success";
	}
	
	@RequestMapping(value="/redis/{key}",method=RequestMethod.GET)
	@ResponseBody
	public Object getRedis(@PathVariable("key")String key){
		Object value = redisService.get(key);
		return value;
	}
}
