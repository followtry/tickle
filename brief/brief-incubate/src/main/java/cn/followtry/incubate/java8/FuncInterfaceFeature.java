/**
 * 
 */
package cn.followtry.incubate.java8;

/**
 * 函数式接口
 * @author jingzz
 * @time 2016年8月31日 上午11:08:42
 * @name testJar/com.shiyanlou.java8.FuncInterfaceFeature
 * @since 2016年8月31日 上午11:08:42
 */
//声明接口为函数式接口，只能有一个抽象方法
@FunctionalInterface
public interface FuncInterfaceFeature {
	
	/*
	 * 函数式接口只能有一个抽象方法，否则会报错
	 */
	
	int operation(int a,int b);
	
//	void sayHello(String message);
}
