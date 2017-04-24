package com.multithread.thread;

/**
 * 继承Thread实现线程
 */
public class ThreadDemo {
	
	public static void main(String[] args) {
		new ThreadByExtends("窗口1").start();
		new ThreadByExtends("窗口2").start();
		new ThreadByExtends("窗口3").start();
	}

}

class ThreadByExtends extends Thread {
	private int ticketsCount = 5;
	private String threadName;
	
	public ThreadByExtends(String threadName) {
		this.threadName = threadName;
	}

	@Override
	public void run() {
		while(ticketsCount > 0) {
			ticketsCount--;
			System.out.println(threadName + "买了一张票，剩余票数为：" + ticketsCount);
		}
	}
	
}
