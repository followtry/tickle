/**
 * 
 */
package cn.jingzztech.prac.testcode;

import java.util.concurrent.TimeUnit;

/**
 * 
 * 代码测试区
 * <p>测试成功的代码会被已到相应的包下</p>
 * @author jingzz
 * @time 2016年5月9日 上午9:21:43
 * @name brief-service/cn.jingzztech.prac.testcode.TestCode
 * @since 2016年5月9日 上午9:21:43
 */
public class TestCode {
	
	public enum TestEnum{
		HEHE(1),
		XIXI(2);
		
		int value;
		
		private TestEnum(int value) {
			this.value = value;
		}
		
		@Override
		public String toString() {
			return values().toString();
		}
	}
	
	public static void main(String[] args) {
		System.out.println("TestCode.main()"+ TimeUnit.DAYS.toDays(System.currentTimeMillis()));
	}
}
