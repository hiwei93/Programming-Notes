package com.multithread.producer_consumer.callable;

import java.util.concurrent.Callable;

/**
 * 生产者：实现了Callable接口
 */
public class Producer implements Callable<String> {
	private Warehouse warehouse;
	private int productId;

	public Producer(Warehouse warehouse, int productId) {
		super();
		this.warehouse = warehouse;
		this.productId = productId;
	}

	@Override
	public String call() throws Exception {
		String information = null;

		Product product = new Product(productId);
		warehouse.enqueue(product);
		information = "生产者 " + Thread.currentThread().getName() + " 生产了"
				+ product;
		try {
			Thread.sleep(productId * 2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return information;
	}

}
