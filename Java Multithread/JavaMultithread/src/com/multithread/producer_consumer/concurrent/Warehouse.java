package com.multithread.producer_consumer.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 存放产品的仓库队列
 */
public class Warehouse {
	
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();

	int front = 0;
	int rear = 0;
	int number = 0;
	Product[] products = new Product[6];
	
	// 入队
	public void enqueue(Product product){
		lock.lock();
		try {
			if (front == rear && number == 6) {
					System.out.println("仓库已满，请稍后生产！");
					condition.await();
					return;
			}
			
			products[rear] = product;
			rear = (rear + 1) % products.length;
			number++;
			condition.signalAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	// 出队
	public Product dequeue() {
		lock.lock();
		Product product = null;
		try {
			if (front == rear && number == 0) {
					System.out.println("仓库已空，请稍后取货！");
					condition.await();
					return product;
			}
			
			product = products[front];
			products[front] = null;
			front = (front + 1) % products.length;
			number--;
			condition.signalAll();
			return product;
		} catch (InterruptedException e) {
			e.printStackTrace();
			return product;
		} finally {
			lock.unlock();
		}
	}
}
