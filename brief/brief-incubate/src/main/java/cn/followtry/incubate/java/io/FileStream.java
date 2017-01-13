package cn.followtry.incubate.java.io;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
public class FileStream {
	
	public static void main(String[] args) {
		String name ="/cn/followtry/incubate/AnnoTest.class";
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		File file = new File(name);
		System.out.println(file.toPath());
		InputStream in = null;
		try {
			in = new FileStream().getClass().getResourceAsStream(name);
			byte[] b = new byte[2048];
			while(in != null && in.read(b) != -1){
				out.write(b);
				out.flush();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		String string = new String(out.toByteArray());
		System.out.println(string);
		
		String path = new FileStream().getClass().getResource("/"+name).getPath();
		System.out.println(path);
		
	}
}
