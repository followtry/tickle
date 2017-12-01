/**
 * 
 */
package cn.followtry.custom.spring;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 跨域访问拦截器
 * @author jingzz
 * @time 2016年10月12日 上午9:34:53
 * @name brief-service/cn.jingzz.brief.custom.CrossInterceptor
 * @since 2016年10月12日 上午9:34:53
 */
public class CrossInterceptor extends HandlerInterceptorAdapter {
	
	private static final Logger LOG = LoggerFactory.getLogger(CrossInterceptor.class);
	

    @Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		response.addHeader("Access-Control-Allow-Origin","*");
        response.addHeader("Access-Control-Allow-Methods","*");
        response.addHeader("Access-Control-Max-Age","100");
        response.addHeader("Access-Control-Allow-Headers", "Content-Type");
        response.addHeader("Access-Control-Allow-Credentials","false");
        LOG.info("允许跨域");
		return super.preHandle(request, response, handler);
	}
	

    @Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		LOG.info("拦截url:"+request.getRequestURI());
		super.postHandle(request, response, handler, modelAndView);
	}
	

    @Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		LOG.info("完成处理:"+request.getRequestURI());
		if (response.getStatus() >= 400) {
			LOG.error("出现处理异常，状态码："+response.getStatus());
			LOG.error("url不存在:"+request.getRequestURI());
			LOG.error("远程主机:"+request.getRemoteHost());
			LOG.error("远程地址:"+request.getRemoteAddr());
			LOG.error("远程端口:"+request.getRemotePort());
			LOG.error("远程用户:"+request.getRemoteUser());
		}
		super.afterCompletion(request, response, handler, ex);
	}
	
	
	
}
