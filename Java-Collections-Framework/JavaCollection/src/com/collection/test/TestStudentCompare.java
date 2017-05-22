package com.collection.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.collection.entity.Student;
import com.collection.list.StudentComparator;

public class TestStudentCompare {

	public static void main(String[] args) {
		
		List<Integer> list = new ArrayList<Integer>();
		
		List<Student> students = new ArrayList<Student>();
		
		Student s1 = new Student(getRandomId(list), "Mike", 10);
		Student s2 = new Student(getRandomId(list), "Nacy", 4);
		Student s3 = new Student(getRandomId(list), "Wolf", 6);
		Student s4 = new Student(10000 + "", "Wolf", 6);
		
		students.add(s1);
		students.add(s2);
		students.add(s3);
		students.add(s4);
		
		System.out.println("********排序前*********");
		for (Student student : students) {
			System.out.println("元素：" + student);
		}
		
		System.out.println("*********按学生Id排序后**********");
		Collections.sort(students);
		for (Student student : students) {
			System.out.println("元素：" + student);
		}
		
		System.out.println("*********按学生年龄排序后**********");
		Collections.sort(students, new StudentComparator());
		for (Student student : students) {
			System.out.println("元素：" + student);
		}
	}
	
	private static String getRandomId(List<Integer> list) {
		Random random = new Random();
		int id = 0;
		
		do {
			id = random.nextInt(1000);
		} while (list.contains(id));
		
		list.add(id);
		return id + "";
		
	}

}
