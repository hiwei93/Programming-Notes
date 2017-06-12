package com.interview.group;

import java.util.Random;

/**
 * 公司里面有男生女生若干，公司每周的周二和周四出去聚餐，需要分组做：
 * 1. 一桌四人，男生女生尽量各两人；
 * 2. 周二和周四的锁分组员均不相同；
 * 3. 周二的分组方法：要求传入两个一维数组，返回一个二维数组；
 * 4. 周四的分组方法：要求以周二的返回结果为参数传入，返回一个二维数组。
 * 为简化，可以先设想男生女生的人数一样，且总人数刚好可以被四整除；
 * 接着可以扩展，考虑人数随意的情况。
 */
public class DivideIntoGroups {
	
	// 周二分组方法
	public String[][] tueGroup(String[] boys, String[] girls) {
		Random random = new Random();
		
		int boysLength = boys.length;
		int girlsLength = girls.length;
		int position; // 存储随机位置
		
		String[][] tueResult = new String[(boysLength + girlsLength) / 4][4];
		
		for(int i = 0; i < tueResult.length; i++){
			for (int j = 0; j < tueResult[i].length; j++) {
				// 奇数位为女生，偶数位为男生
				if(j % 2 == 0){
					position = random.nextInt(boysLength);
					while (boys[position] == null) {
						position = random.nextInt(boysLength);
					}
					tueResult[i][j] = boys[position];
					boys[position] = null;
				} else {
					position = random.nextInt(girlsLength);
					while (girls[position] == null) {
						position = random.nextInt(girlsLength);
					}
					tueResult[i][j] = girls[position];
					girls[position] = null;
				}
			}
		}
		
		return tueResult;
	}
	
	// 周四的分组方法
	/**
	 * 目前最好的实现方法，在矩阵实现的基础上改进，可以参考图reference.png
	 * 兼顾了随机性和健壮性:
	 * @param tueResult
	 * @return
	 */
	public String[][] thuGroup(String[][] tueResult) {
		Random random = new Random();
		int row = tueResult.length;
		int column = tueResult[0].length;
		int position; 
		int rowNum;
		
		String[][] thuResult = new String[row][column];
		for (int i = 0; i < thuResult.length; i++) {
			for (int j = 0; j < thuResult[i].length; j++) {
				rowNum = (i + j) % row;
				position = random.nextInt(tueResult[rowNum].length);
				if(j % 2 == 0) {
					while (position % 2 != 0 || tueResult[rowNum][position] == null) {
						position = random.nextInt(tueResult[rowNum].length);
					}
				} else {
					while (position % 2 == 0 || tueResult[rowNum][position] == null) {
						position = random.nextInt(tueResult[rowNum].length);
					}
				}
				thuResult[i][j] = tueResult[rowNum][position];
				tueResult[rowNum][position] = null;
			}
		}		
		return thuResult;
	}
	
	/**
	 * 该方法是引用矩阵的思想实现的，牺牲了随机性
	 * @param tueResult
	 * @return
	 
	public String[][] thuGroup(String[][] tueResult) {
		int row = tueResult.length;
		int column = tueResult[0].length;
		
		String[][] thuResult = new String[row][column];
		
		for (int i = 0; i < thuResult.length; i++) {
			for (int j = 0; j < thuResult[i].length; j++) {
				thuResult[i][j] = tueResult[(i + j) % row][j];
			}
		}
		return thuResult;
	}*/

	/**
	 * 该方法只能用于16人分组，对于20人分组会出现死循环。
	 * @param tueResult
	 * @return
	 
	public String[][] thuGroup(String[][] tueResult) {
		Random random = new Random();
		int row = tueResult.length;
		int column = tueResult[0].length;
		int rowPosition; // 存储随机行的位置
		int colPosition; // 存储随机列的位置
		List<Integer> rowList = new ArrayList<Integer>(); // 用于存储随机行数，实现选择不同行
		
		String[][] thuResult = new String[row][column];
		
		for (int i = 0; i < thuResult.length; i++) {
			for (int j = 0; j < thuResult[i].length; j++) {
				
				if(j % 2 == 0) {
					rowPosition = random.nextInt(row);
					while (rowList.contains(rowPosition) || !rowIsUseful(tueResult[rowPosition], "boy")) {
						rowPosition = random.nextInt(row);
					}
					rowList.add(rowPosition);
					
					colPosition = random.nextInt(tueResult[rowPosition].length);
					while(colPosition % 2 != 0 || tueResult[rowPosition][colPosition] == null) {
						colPosition = random.nextInt(tueResult[rowPosition].length);
					}
				} else {
					rowPosition = random.nextInt(row);
					while (rowList.contains(rowPosition) || !rowIsUseful(tueResult[rowPosition], "girl")) {
						rowPosition = random.nextInt(row);
					}
					rowList.add(rowPosition);
					
					colPosition = random.nextInt(tueResult[rowPosition].length);
					while(colPosition % 2 == 0 || tueResult[rowPosition][colPosition] == null) {
						colPosition = random.nextInt(tueResult[rowPosition].length);
					}
				}
				thuResult[i][j] = tueResult[rowPosition][colPosition];
				tueResult[rowPosition][colPosition] = null;
			}
			rowList.clear();
		}
		
		return thuResult;
	}

	private boolean rowIsUseful(String[] arr, String sex) {
		for (int i = 0; i < arr.length; i++) {
			if("boy".equals(sex)){
				if(i % 2 == 0 && arr[i] != null){
					return true;
				}
			}
			
			if("girl".equals(sex)){
				if(i % 2 != 0 && arr[i] != null){
					return true;
				}
			}
		}
		return false;
	}*/
	
	/**
	 * 该方法错误：
	 * 会出现无限循环的情况，
	 * 如某一行的奇数或偶数位均为Null，则会在该行无限循环
	 * @param tueResult
	 * @return
	 
	public String[][] thuGroup(String[][] tueResult) {
		Random random = new Random();
		int row = tueResult.length;
		int column = tueResult[0].length;
		int rowPosition; // 存储随机行的位置
		int colPosition; // 存储随机列的位置
		List<Integer> rowList = new ArrayList<Integer>(); // 用于存储随机行数，实现选择不同行
		
		String[][] thuResult = new String[row][column];
		
		for (int i = 0; i < thuResult.length; i++) {
			for (int j = 0; j < thuResult[i].length; j++) {
				
				rowPosition = random.nextInt(row);
				while (rowList.contains(rowPosition)) {
					rowPosition = random.nextInt(row);
				}
				rowList.add(rowPosition);
				
				colPosition = random.nextInt(tueResult[rowPosition].length);
				if (j % 2 == 0) {
					while(colPosition % 2 != 0 || tueResult[rowPosition][colPosition] == null) {
						colPosition = random.nextInt(tueResult[rowPosition].length);
					}
				} else {
					while (colPosition % 2 == 0 || tueResult[rowPosition][colPosition] == null) {
						colPosition = random.nextInt(tueResult[rowPosition].length);
					}
				}
				thuResult[i][j] = tueResult[rowPosition][colPosition];
				tueResult[rowPosition][colPosition] = null;
			}			
			rowList.clear();
		}
		return thuResult;
	}*/

}
