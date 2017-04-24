package com.multithread.producer_consumer.thread;

/**
 * 生产者-消费者问题（通过Thread实现）
 */
public class ProducerConsumerProblemByThread {
	public static void main(String[] args) {
		Warehouse warehouse = new Warehouse();
		Producer producer = new Producer(warehouse);
		Consumer consumer1 = new Consumer(warehouse);
		Consumer consumer2 = new Consumer(warehouse);
		
		new Thread(producer).start();
		new Thread(consumer1).start();	
		new Thread(consumer2).start();
	}
}
