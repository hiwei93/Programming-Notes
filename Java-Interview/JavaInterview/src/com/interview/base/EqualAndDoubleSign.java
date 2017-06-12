package com.interview.base;

public class EqualAndDoubleSign {
	
	public static void main(String[] args) {
		Object a = new Object();
		Object b = a;
		Object c = a;
		
		System.out.println(b == c);
	}
	
	public void functionA() {
		System.out.println("I'm function A");
	}

	public void functionA(String str) {
		System.out.println("I'm function A with " + str);
	}

	public String functionA(int i) {
		return "I'm function A with " + i;
	}
	
	public String functionA(String str, int i) {
		return "I'm function A with " + i;
	}
	
	public String functionA(int i, String str) {
		return "I'm function A with " + i;
	}
}
