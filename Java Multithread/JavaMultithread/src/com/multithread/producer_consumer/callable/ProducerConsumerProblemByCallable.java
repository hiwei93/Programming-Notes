package com.multithread.producer_consumer.callable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
/**
 * 生产者-消费者问题（通过操作Callable的方式实现）
 */
public class ProducerConsumerProblemByCallable {
	public static void main(String[] args) {
		Warehouse warehouse = new Warehouse();		
		
		ExecutorService executorService = Executors.newCachedThreadPool();
		List<Future<String>> futures = new ArrayList<Future<String>>();
		
		for (int i = 1; i <= 10; i++) {
			Future<String> producerFuture = executorService.submit(new Producer(warehouse, i));
			futures.add(producerFuture);
			Future<String> consumerFuture = executorService.submit(new Consumer(warehouse));
			futures.add(consumerFuture);
		}
		
//		for (int i = 1; i <= 10; i++) {
//		}
		
		try {
			for (Future<String> future : futures) {
				while(!future.isDone());
				System.out.println(future.get());
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} finally {
			executorService.shutdown();
		}
	}
}
