package com.multithread.producer_consumer.thread;

/**
 * 产品类
 */
public class Product {
	
	private int id;

	public Product(int id) {
		super();
		this.id = id;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + "]";
	}
}
