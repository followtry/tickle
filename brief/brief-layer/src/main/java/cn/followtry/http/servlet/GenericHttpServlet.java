package cn.followtry.http.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  brief-layer/cn.followtry.http.servlet.GenericHttpServlet
 * @author 
 *		jingzz 
 * @since 
 *		2016年12月29日 上午10:53:06
 */
public class GenericHttpServlet extends HttpServlet {

	/**  */
	private static final long serialVersionUID = -8582002525279015681L;
	

    @Override
	public void init() throws ServletException {
		super.init();
	}
	

    @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("GenericHttpServlet.doGet()");
		doPost(req, resp);
	}
	

    @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("GenericHttpServlet.doPost()2");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");
		resp.getWriter().write("欢迎访问servlet原生web程序ddd");
	}
}
