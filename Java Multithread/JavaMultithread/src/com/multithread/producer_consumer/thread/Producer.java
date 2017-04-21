package com.multithread.producer_consumer.thread;

public class Producer implements Runnable {
	private Warehouse warehouse;

	public Producer(Warehouse warehouse) {
		super();
		this.warehouse = warehouse;
	}

	@Override
	public void run() {
		for(int i = 1; i <= 10; i++) {
			Product product = new Product(i);
			warehouse.enqueue(product);
			System.out.println("生产者 " + Thread.currentThread().getName() + " 生产了" + product);
			try {
				Thread.sleep(i * 2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
