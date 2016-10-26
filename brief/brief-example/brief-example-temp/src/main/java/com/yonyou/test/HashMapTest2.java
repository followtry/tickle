package com.yonyou.test;

import java.util.HashMap;

public class HashMapTest2 {
	public static void main(String[] args) throws InterruptedException {
		double sum = 0;
		int time = 20;
		for (int i = 0; i < 20; i++) {
			time += testMapPerformance();
		}
		System.out.println(time+"次平均用时为:"+(sum/time));
		System.out.println("结束");
	}

	/**
	 * @author jingzz
	 * @return
	 */
	protected static double testMapPerformance() {
		long starTime = System.currentTimeMillis();
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		for (double i = 0; i < 1000; i++) {
			map.put(i, i);
		}
		long inteval = System.currentTimeMillis() - starTime;
		double perTime = inteval / 1000.0;
		System.out.println(perTime);
		return perTime;
	}
}
