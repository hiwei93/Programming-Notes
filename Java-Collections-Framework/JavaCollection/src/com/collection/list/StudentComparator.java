package com.collection.list;

import java.util.Comparator;

import com.collection.entity.Student;

public class StudentComparator implements Comparator<Student> {

	/**
	 * 实现Comparator的compare()方法
	 * 临时比较规则：根据年龄进行比较
	 */
	@Override
	public int compare(Student o1, Student o2) {
		return o1.getAge().compareTo(o2.getAge());
	}

}
