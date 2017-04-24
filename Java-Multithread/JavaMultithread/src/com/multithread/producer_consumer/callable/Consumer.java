package com.multithread.producer_consumer.callable;

import java.util.concurrent.Callable;

/**
 * 消费者：实现了Callable接口
 */
public class Consumer implements Callable<String> {
	private Warehouse warehouse;
	
	public Consumer(Warehouse warehouse) {
		super();
		this.warehouse = warehouse;
	}

	@Override
	public String call() throws Exception {
		String information = null;
		Product product = null;
		
		product = warehouse.dequeue();
		if (product != null) {
			information = "消费者 " + Thread.currentThread().getName() + " 消费了"
					+ product;
		}
		try {
			Thread.sleep(20 / product.getId());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
			
		return information;
	}
}
