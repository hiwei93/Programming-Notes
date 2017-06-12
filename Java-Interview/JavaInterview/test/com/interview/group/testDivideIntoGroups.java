package com.interview.group;

public class testDivideIntoGroups {

	public static void main(String[] args) {
		DivideIntoGroups groups = new DivideIntoGroups();
		
		String[] boys = {"张三", "李四", "王五", "赵四", "牛二", "大周", "小刘", "马哥", "小明", "包哥"};
		String[] girls = {"丽丽", "阿梅", "花花", "小美", "妞妞", "小英", "阿娟", "婷婷", "阿静", "妮妮"};
//		String[] boys = {"张三", "李四", "王五", "赵四", "牛二", "大周", "小刘", "马哥"};
//		String[] girls = {"丽丽", "阿梅", "花花", "小美", "妞妞", "小英", "阿娟", "婷婷"};
		
		String[][] tueResult = groups.tueGroup(boys, girls);
		System.out.println("周二的分组情况：");
		for (String[] strings : tueResult) {
			for (String string : strings) {
				System.out.print(string + ", ");
			}
			System.out.println();
		}
		
		String[][] thuResult = groups.thuGroup(tueResult);
		System.out.println("周四的分组情况：");
		for (String[] strings : thuResult) {
			for (String string : strings) {
				System.out.print(string + ", ");
			}
			System.out.println();
		}

	}

}
