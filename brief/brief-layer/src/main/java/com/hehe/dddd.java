/**
 * 
 */
package com.hehe;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Properties;

/**
 * @author jingzz
 * @time 2016年6月1日 下午12:49:20
 * @name brief-layer/com.hehe.dddd
 * @since 2016年6月1日 下午12:49:20
 */
public class dddd {
	public static void main(String args[]) throws Exception {
		Properties ps = System.getProperties();
		Iterator<Entry<Object, Object>> it = ps.entrySet().iterator();
		while(it.hasNext()){
			Entry<Object, Object> entry = it.next();
			if(entry.getKey().toString().startsWith("java")){
				System.out.println(entry.getKey()+":"+entry.getValue());
			}
		}
	}
}
