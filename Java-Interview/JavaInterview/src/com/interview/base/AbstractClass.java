package com.interview.base;

/**
 * 抽象类
 */
abstract class Animal {
	
	public void saySomething() {
		System.out.println("I can be any kind of animal!");
	}

}

/**
 * 实现类
 */
class Cat extends Animal {

	@Override
	public void saySomething() {
		System.out.println("I can be a cat!");
	}

}
