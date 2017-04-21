package com.multithread.producer_consumer.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * 生产者-消费者问题（通过Concurrent下的类实现，如线程池、clocks类）
 */
public class ProducerConsumerProblemByConcurrent {
	public static void main(String[] args) {
		Warehouse warehouse = new Warehouse();
		
		ExecutorService executorService = Executors.newCachedThreadPool();
		executorService.execute(new Producer(warehouse));
		executorService.execute(new Consumer(warehouse));
		
		executorService.shutdown();
	}
}
