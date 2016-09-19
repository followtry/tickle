package com.yonyou.test;

import java.util.concurrent.ConcurrentHashMap;

public class ConCurrentHashMapTest2 {
	public static <U> void main(String[] args) throws InterruptedException {
		ConcurrentHashMap<Object, Object> map = new ConcurrentHashMap<>();
		map.put("jingzz", "eee");
		map.put("jingzz", "ccc");
		map.put("jingzz2", "aaa");
		map.put("jingzz3", "bbb");
		map.put("jingzz3", "ddd");
		map.get("jingzz");
		map.remove("jingzz");
	}
}
