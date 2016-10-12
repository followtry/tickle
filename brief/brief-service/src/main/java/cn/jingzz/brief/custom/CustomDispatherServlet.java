/**
 * 
 */
package cn.jingzz.brief.custom;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * @author jingzz
 * @time 2016年9月17日 下午12:12:54
 * @name yycollege/com.yonyou.esn.yycollege.common.MyDispatherServlet
 * @since 2016年9月17日 下午12:12:54
 */
public class CustomDispatherServlet extends DispatcherServlet {

	/**  */
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOG = LoggerFactory.getLogger(CustomDispatherServlet.class);
	
	@Override
	protected void noHandlerFound(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LOG.error("url不存在:"+request.getRequestURI());
		LOG.error("远程主机:"+request.getRemoteHost());
		LOG.error("远程地址:"+request.getRemoteAddr());
		LOG.error("远程端口:"+request.getRemotePort());
		LOG.error("远程用户:"+request.getRemoteUser());
		super.noHandlerFound(request, response);
	}
	
	
	
}
