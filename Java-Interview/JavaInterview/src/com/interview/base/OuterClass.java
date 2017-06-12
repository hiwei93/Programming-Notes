package com.interview.base;

public class OuterClass {
	
	public class InnerClass{
		public void show() {
			System.out.println("I'm a inner Class!");
		}
	}
	
	public static class StaticInnerClass {
		public void show() {
			System.out.println("I'm a static inner Class!");
		}
	}
	
	
	public static void main(String[] args) {
		OuterClass outer = new OuterClass();
		InnerClass inner = outer.new InnerClass();
		inner.show();
		
		
		
		StaticInnerClass sClass = new StaticInnerClass();
		sClass.show();
	}

}
