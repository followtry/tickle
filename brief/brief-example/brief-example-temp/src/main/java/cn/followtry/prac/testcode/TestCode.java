/**
 * 
 */
package cn.followtry.prac.testcode;

import java.util.HashMap;

/**
 * 
 * 代码测试区
 * <p>测试成功的代码会被已到相应的包下</p>
 * @author jingzz
 * @time 2016年5月9日 上午9:21:43
 * @name brief-service/cn.jingzztech.prac.testcode.TestCode
 * @since 2016年5月9日 上午9:21:43
 */
public class TestCode {
	
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		
		String className = HashMap.class.getCanonicalName();
		Class clazz = Class.forName(className );
		
		Object instance = clazz.newInstance();
		System.out.println(instance);
	}
	
}
