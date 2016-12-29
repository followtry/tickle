/**
 * 
 */
package cn.followtry.incubate;

/**
 * @author jingzz
 * @time 2016年2月18日 上午11:31:35
 * @func
 * @name IntegerTest
 */
public class IntegerTest {
	public static void main(String[] args) {
		int availableProcessors = Runtime.getRuntime().availableProcessors();
		System.out.println("CPU数量：" + availableProcessors);
		System.out.println(16 << 2);
		System.out.println(16 << 2 << 3);
	}

}
