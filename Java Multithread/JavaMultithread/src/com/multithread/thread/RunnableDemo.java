package com.multithread.thread;

public class RunnableDemo {
	
	public static void main(String[] args) {
		ThreadByImp thread = new ThreadByImp();
		new Thread(thread, "窗口1").start();
		new Thread(thread, "窗口2").start();
		new Thread(thread, "窗口3").start();
	}

}

class ThreadByImp implements Runnable {
	private int ticketsCount = 5;
	
	@Override
	public void run() {
		while(ticketsCount > 0) {
			ticketsCount--;
			System.out.println(Thread.currentThread().getName() + "买了一张票，剩余票数为：" + ticketsCount);
		}
	}
}