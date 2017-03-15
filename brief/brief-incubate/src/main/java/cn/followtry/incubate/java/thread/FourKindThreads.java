package cn.followtry.incubate.java.thread;

import java.util.concurrent.*;

/**
 * 线程创建的四种方式
 *
 * Created by Administrator on 2017/2/16.
 */
public class FourKindThreads {

	private static Thread thread;

	public static void main(String[] args) {

		//1.通过实现Runnable接口创建线程
		thread = new Thread(new TestRunnable());
		thread.start();

		//2.通过继承Thread类创建线程
		thread = new TestThread();
		thread.start();

		//3.通过实现Callable接口创建线程
		TestCall call = new TestCall();
		FutureTask<Integer> futureTask = new FutureTask<Integer>(call);
		thread = new Thread(futureTask);
		thread.start();
		try {
			Integer value = futureTask.get();
			System.out.println("value:"+value);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

		//通过ExecutorService接口创建线程或线程池
		ExecutorService executorService = Executors.newCachedThreadPool();
		executorService.execute(new TestRunnable());



	}
}


class TestRunnable implements  Runnable{

	@Override
	public void run() {
		System.out.println("cn.followtry.incubate.java.thread.TestRunnable.run()");
	}
}

class TestThread extends Thread{
	@Override
	public void run() {
		System.out.println("cn.followtry.incubate.java.thread.TestThread.run()");
	}
}

class  TestCall implements Callable<Integer>{

	@Override
	public Integer call() throws Exception {
		System.out.println("cn.followtry.incubate.java.thread.TestCallable.call()");
		return  200;
	}
}