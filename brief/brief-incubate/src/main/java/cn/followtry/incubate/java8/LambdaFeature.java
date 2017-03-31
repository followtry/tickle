package cn.followtry.incubate.java8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * lambda表达式<br/>
 * 1.集合的新旧表示方式：foreach和lambda表达式，<b>
 * 2.在集合创建时指定初始值
 * Created by followtry on 2017/3/27 0027.
 */
public class LambdaFeature {
	public static void main(String[] args) {
		//在创建list集合时就添加数据
		List<String> list=new ArrayList() {
			{
				for (int i=0; i < 5; i++) {
					add("jingzz" + i);
				}
				add("jingzz");
				add("zhong");
				add("zhi");
				add("jingzz123");
			}
		};

		newLambdaWay(list);
		System.out.println("===========================================");
		oldWay(list);
	}

	/**
	 * 新的lambda方式操作集合
	 *
	 * @param list
	 */
	private static void newLambdaWay(List<String> list) {
		/*
			方式一：较完整的lambda表达方法
		 */
		list.stream().sorted((String o1, String o2) -> {
			return o1 == null && o1 == o2 ? 0 : o1.compareTo(o1);
		}).forEach(str -> {
			System.out.println(str);
		});

		/*
		方式二：简化lambda表示式书写，因为如果方法体中只有一行代码，则可以将花括号和return省略
		 */
		list.stream().sorted((o1, o2) -> o1 == null && o1 == o2 ? 0 : o1.compareTo(o1)).forEach(str -> System.out.println(str));
	}

	/**
	 * 原先方式操作集合
	 *
	 * @param list
	 */
	private static void oldWay(List<String> list) {
		Collections.sort(list, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1 == null && o1 == o2 ? 0 : o1.compareTo(o1);
			}
		});
		for (String str : list) {
			System.out.println(str);
		}
	}
}
