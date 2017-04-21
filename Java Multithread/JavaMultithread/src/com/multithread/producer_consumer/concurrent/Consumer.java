package com.multithread.producer_consumer.concurrent;

public class Consumer implements Runnable {
	private Warehouse warehouse;
	
	public Consumer(Warehouse warehouse) {
		super();
		this.warehouse = warehouse;
	}

	@Override
	public void run() {
		Product product = null;
		
		for (int i = 1; i <= 10; i++) {
			product = warehouse.dequeue();
			if(product != null) {
				System.out.println("消费者 " + Thread.currentThread().getName() + " 消费了" + product);
			}
			try {
				Thread.sleep(20 / i);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
