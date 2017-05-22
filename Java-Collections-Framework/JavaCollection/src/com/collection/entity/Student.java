package com.collection.entity;

public class Student implements Comparable<Student> {
	
	private String id;
	private String name;
	private Integer age;
	
	public Student(String id, String name, Integer age) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", age=" + age + "]";
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
	/**
	 * 实现Comparable接口的compareTo()方法
	 * 默认比较规则为：根据ID进行比较
	 */
	@Override
	public int compareTo(Student o) {
		return Integer.valueOf(this.id).compareTo(Integer.valueOf(o.id));
	}
	
}
