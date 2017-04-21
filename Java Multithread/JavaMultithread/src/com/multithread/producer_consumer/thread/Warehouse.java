package com.multithread.producer_consumer.thread;

/**
 * 存放产品的仓库队列
 */
public class Warehouse {

	int front = 0;
	int rear = 0;
	int number = 0;
	Product[] products = new Product[6];
	
	// 入队
	public synchronized void enqueue(Product product){
		if (front == rear && number == 6) {
			try {
				System.out.println("仓库已满，请稍后生产！");
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return;
		}
		
		products[rear] = product;
		rear = (rear + 1) % products.length;
		number++;
		this.notifyAll();
	}
	
	// 出队
	public synchronized Product dequeue() {
		Product product = null;
		
		if (front == rear && number == 0) {
			try {
				System.out.println("仓库已空，请稍后取货！");
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			return product;
		}
		
		product = products[front];
		products[front] = null;
		front = (front + 1) % products.length;
		number--;
		this.notifyAll();
		return product;
	}
}
