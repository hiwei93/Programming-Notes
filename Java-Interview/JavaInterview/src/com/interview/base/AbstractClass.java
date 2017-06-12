package com.interview.base;

abstract class AbstractClass {
	
	abstract void run();
	
	public void test(String something) {
		System.out.println("there have something " + something);
	}

}

class saySomething extends AbstractClass {

	@Override
	void run() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void test(String something) {
		// TODO Auto-generated method stub
		System.out.println("override something!");
	}
}
