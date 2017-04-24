package com.multithread.producer_consumer.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 使用Callable的一个例子
 * @author wei11
 *
 */
public class CallableDemo {
	
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newCachedThreadPool();
		try {
			for (int i = 1; i <= 10; i++) {
				Future<String> future = executorService
						.submit(new CallableTest(i));
				while (!future.isDone());
				System.out.println(future.get() + "" + future.isDone());				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		executorService.shutdown();
	}
	

}

class CallableTest implements Callable<String> {
	private int id;
	
	public CallableTest(int id) {
		super();
		this.id = id;
	}

	@Override
	public String call() throws Exception {
		Product product = new Product(id);
		return "生产者" + Thread.currentThread().getName()	+ "生产了产品" + product;
	}	
}
