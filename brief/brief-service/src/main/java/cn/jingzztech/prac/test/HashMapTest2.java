package cn.jingzztech.prac.test;

import java.util.HashMap;
import java.util.Map;

public class HashMapTest2 {
	public static void main(String[] args) throws InterruptedException {
		double sum = 0.0;
		int number = 10000000;
		int time = 20;
		
		Map<Object, Object> putMap = testMapPutPerformance(time,number);
		testMapGetPerformance(time, putMap, number);
		System.out.println("结束");
	}

	/**
	 * @author jingzz
	 * @return
	 */
	private static Map<Object, Object> testMapPutPerformance(int time,int number) {
		Map<Object, Object> map = null;
		double sum = 0.0;
		for (int j = 0; j < time; j++) {
			map = new HashMap<Object, Object>();
			long starTime = System.currentTimeMillis();
			for (Double i = (double) 0; i < number; i++) {
				map.put(i, i);
			}
			long inteval = System.currentTimeMillis() - starTime;
			double perTime = inteval / 1000.0;
			System.out.println("put:"+perTime);
			sum += perTime;
		}
		System.out.println(time+"次put平均用时为:"+(sum/time));
		return map;
	}

	/**
	 * @author jingzz
	 * @param times
	 * @param map
	 */
	protected static void testMapGetPerformance(int time, Map<Object, Object> map,int number) {
		double sum = 0.0;
		for (int j = 0; j < time; j++) {
			long starTime = System.currentTimeMillis();
			for (Double i = (double) 0; i < number; i++) {
				map.get(i);
			}
			long inteval = System.currentTimeMillis() - starTime;
			double perTime = inteval / 1000.0;
			System.out.println("get:"+perTime);
			sum += perTime;
		}
		System.out.println(time+"次get平均用时为:"+(sum/time));
	}
}
