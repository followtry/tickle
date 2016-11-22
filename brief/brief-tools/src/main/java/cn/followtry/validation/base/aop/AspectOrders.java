package cn.followtry.validation.base.aop;

/**
 * AspectOrder 定义了各类切面的顺序的常量；
 * 
 * @author haiq
 *
 */
public interface AspectOrders {
	
	/**
	 * 日志；
	 */
	public static final int LOGGING = 0;
	
	/**
	 * 数据校验；
	 */
	public static final int VALIDATION = 100;
	
	/**
	 * 操作授权校验；
	 */
	public static final int AUTH_CHECKING = 200;
	
}
