/**
 * 
 */
package cn.followtry.java8.functional;

/**
 * 接口中有单一的方法声明时，即使没有{@link FunctionalInterface}注解声明也可以以lambda表达式使用。
 * Created by jingzhongzhi on 2017/9/17.
 */
public interface GreetingService {
	
	void sayMessage(String message);
}
