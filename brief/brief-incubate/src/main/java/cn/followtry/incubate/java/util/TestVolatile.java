package cn.followtry.incubate.java.util;

import java.util.concurrent.TimeUnit;

/**
 * 测试{@code volatile}关键字<br>
 * 
 * volatile在多线程操作共享数据时，可以保证内存中数据可见，相较于synchronized是较轻量级的同步策略
 * 
 * volatile不具备互斥性和不能保证变量的原子性
 * 
 * @author jingzz
 * @since
 * 
 * @version
 * 
 */
public class TestVolatile {
	
	public static void main(String[] args) throws InterruptedException {
		ThreadDemo td = new ThreadDemo();
		System.out.println("TestVolatile.main()1");
		Thread thread = new Thread(td);
		thread.start();
		System.out.println("TestVolatile.main()2");
		while (true) {
			if (td.getFlag()) {
				System.out.println("TestVolatile.main()---");
				break;
			}
		}
		System.out.println("结束");
	}

}

class ThreadDemo implements Runnable {
	/**
	 * 多个线程都有一份独有的数据，会根据规则将数据刷新到主内存中。而且两个线程间数据是不可见的。
	 * 在不加volatile时，一个线程的数据不会及时刷新到主内存中。加上volatile后线程的数据就可以实时刷新到主内存，也可以认为是线程直接修改主内存的数据，以保证多个线程的数据可见。
	 * 
	 * 
	 */
	private volatile boolean flag = false;

	@Override
	public void run() {
		System.out.println("线程开启");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		flag = true;
		System.out.println("ThreadDemo.run()" + flag);
	}
	
	public boolean getFlag(){
		return flag;
	}
}
