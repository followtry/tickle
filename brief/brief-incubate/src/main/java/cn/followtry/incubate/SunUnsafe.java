/**
 * 
 */
package cn.followtry.incubate;

import java.util.HashMap;

/**
 * @author jingzz
 * @time 2016年9月6日 下午2:27:48
 * @name testJar/com.yonyou.test.SunUnsafe
 * @since 2016年9月6日 下午2:27:48
 */
public final class SunUnsafe {
	
//	private static sun.misc.Unsafe U;
	
	public static void main(String[] args) {
//		Unsafe unsafe = U.getUnsafe();
		
		HashMap<Object, Object> hashMap = new HashMap<Object,Object>(9);
		hashMap.keySet();
	}

}
