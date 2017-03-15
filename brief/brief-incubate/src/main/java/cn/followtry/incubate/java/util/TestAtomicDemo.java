package cn.followtry.incubate.java.util;

import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author 
 *		jingzz 
 * @since 
 *		
 * @version
 *    
 */
public class TestAtomicDemo {
	
	public static void main(String[] args) {
		AtomicDemo ad = new AtomicDemo();
		
		for (int i = 0; i < 15; i++) {
			new Thread(ad).start();
		}
	}

}

class AtomicDemo implements Runnable{
	
	private  AtomicLong serialNum = new AtomicLong(0L);
	
	@Override
	public void run() {
//		try {
//			TimeUnit.SECONDS.sleep(1);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName()+":"+ getSerialNum());
	}
	
	public long getSerialNum(){
		return serialNum.getAndIncrement();
	}
}
