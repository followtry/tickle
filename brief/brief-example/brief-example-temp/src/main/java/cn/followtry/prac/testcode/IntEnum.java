/**
 * 
 */
package cn.followtry.prac.testcode;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author jingzz
 * @time 2016年5月10日 上午10:58:37
 * @name brief-service/cn.jingzztech.prac.testcode.IntEnum
 * @since 2016年5月10日 上午10:58:37
 */
public interface IntEnum {
	
	int hello();
	
	enum IntEnum2 implements IntEnum{
		;//一定要有
		@Override
		public int hello() {
			return 0;
		}
	}
	
	enum IntEnum3 implements IntEnum{
		;//一定要有
		@Override
		public int hello() {
			return 0;
		}
	}
}
