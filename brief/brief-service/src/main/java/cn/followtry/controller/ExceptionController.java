/**
 * 
 */
package cn.followtry.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 利用注解统一处理异常
 * @author jingzz
 * @time 2016年10月10日 下午4:03:31
 * @name brief-service/cn.jingzz.brief.controller.ExceptionController
 * @since 2016年10月10日 下午4:03:31
 */
@ControllerAdvice
public class ExceptionController {
	
	private static final Logger LOG = LoggerFactory.getLogger(ExceptionController.class);
	
	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
	public void test2(RuntimeException bException,HttpServletResponse response) throws IOException{
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(bException.getMessage());
		LOG.info("RuntimeException系统报异常",bException);
	}
}
