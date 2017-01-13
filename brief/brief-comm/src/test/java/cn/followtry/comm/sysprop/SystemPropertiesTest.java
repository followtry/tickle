/**
 * 
 */
package cn.followtry.comm.sysprop;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * @author jingzz
 * @time 2016年8月5日 下午3:56:47
 * @name esn-palmyy-plugin/com.yonyou.esn.palmyy.service.test
 * @since 2016年8月5日 下午3:56:47
 */
public class SystemPropertiesTest {
	
	/*
	 * 通过反射的方式实例化只含有私有构造器的工具类，并通过反射调用该类的所有方法
	 */
	
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		String className = SystemProperties.class.getName();

		Class<?> clazz = Class.forName(className);
		Method[] declaredMethods = clazz.getDeclaredMethods();
		int i = 0;
		Map<String,String> resMap = new TreeMap<String,String>();
		for (Method method : declaredMethods) {

			System.out.println("方法名：" + method.getName());
			if (Modifier.isPublic(method.getModifiers()) && method.getParameterCount() == 0) {
				i++;
				Object res = method.invoke(clazz);
				resMap.put(method.getName(), res.toString());
			}
		}
		
		for (Entry<String, String> entry : resMap.entrySet()) {
			System.out.println(entry.getKey()+"="+entry.getValue());
		}
		
		
		
		

		System.out.println("i=" + i);
		System.out.println("结束");
	}

	/**
	 * 通过反射实例化只含有私有构造器的类
	 * @author jingzz
	 * @param clazz
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 */
	@SuppressWarnings("unused")
	private static Object getInstance(Class<?> clazz)
			throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Constructor<?> constructor = clazz.getDeclaredConstructor();
		constructor.setAccessible(true);
		Object sysObj = constructor.newInstance();
		return sysObj;
	}
}
