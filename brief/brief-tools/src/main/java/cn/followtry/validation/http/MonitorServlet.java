package cn.followtry.validation.http;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author 
 *		jingzz 
 * @since 
 *		
 * @version
 *    
 */
@WebServlet(urlPatterns="/monitor/*")
public class MonitorServlet extends HttpServlet{
	
	/**  */
	private static final long serialVersionUID = 8428868101362340845L;
	
	private static final String REAL_PATH = "/cn/followtry/springboot/";

	private static final int DEFAULT_BUFFER_SIZE = 4096;

    @Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		super.service(req, resp);
	}
	
    @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");
		final int eof = -1;
		String path = REAL_PATH+"/index.html";
		String path2 = this.getClass().getResource("/").getPath();
		System.out.println(path2);
		InputStream in = this.getClass().getResourceAsStream(path);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];

        int n = 0;
        String content = null;
        try {
			while (eof != (n = in.read(buffer))) {
			    out.write(buffer, 0, n);
			    out.flush();
			}
			content = new String(out.toByteArray());
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println(content);
		try {
			resp.getWriter().write(content);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
