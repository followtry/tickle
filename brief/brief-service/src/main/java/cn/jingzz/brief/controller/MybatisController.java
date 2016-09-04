/**
 * 
 */
package cn.jingzz.brief.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jingzz.brief.dao.model.MyBatis;
import cn.jingzz.brief.service.MybatisService;

/**
 * @author jingzz
 * @time 2016年6月2日 下午9:03:56
 * @name brief-service/cn.jingzz.brief.controller.TestController
 * @since 2016年6月2日 下午9:03:56
 */
@Controller
public class MybatisController {
	
	@Value("${myuser.username}")
	private String name;
	
	@Autowired
	private MybatisService testService;
	
	@RequestMapping(value="/mybatis/{id}",method=RequestMethod.GET)
	@ResponseBody
	public MyBatis test(@PathVariable("id")String id, HttpServletRequest request,HttpServletResponse response){
		MyBatis test = testService.test(id);
		System.out.println(test+","+name);
		return test;
	}
}
