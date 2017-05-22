package com.collection.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * 练习：
 */
public class StringListSort {
	
	/**
	 * 按照原始排序规则对随机字符串排序
	 */
	public void RandomChars() {
		
		List<String> stringList = getRandomStringsList(10);
		
		System.out.println("**********排序**********");
		Collections.sort(stringList);
		for (String string : stringList) {
			System.out.println("元素：" + string);
		}		
	}
	
	/**
	 * 获得一个元素为随机字符串的List
	 * 每个字符串元素的长度是10以内的随机整数
	 * @param listSize
	 * @return
	 */
	public List<String> getRandomStringsList(int listSize) {
		List<String> randomStringsList = new ArrayList<String>(listSize);
		
		Random random = new Random();

		String randomString = null;

		// 添加10条随机字符
		for (int i = 0; i < 10; i++) {
			// 每条随机字符长度10以内的随机整数
			int length = 0;
			do {
				length = random.nextInt(10);
			} while (length == 0);

//			System.out.println(length);

			// 生成不重复的随机字符
			do {
				randomString = getRandomString(length);
			} while (randomStringsList.contains(randomString));

			randomStringsList.add(randomString);
			System.out.println("成功添加：" + randomString);
		}
		
		return randomStringsList;
	}
	
	/**
	 * 生成随机字符串
	 * @param stringLength
	 * @return
	 */
	private String getRandomString(int stringLength){
		final String base = "0123456789abcdefghigklmnopqrstuvwxyzABCDEFGHIGKLMNOPQRSTUVWXYZ";
		
		Random random = new Random();
		StringBuffer randomString = new StringBuffer();
		
		for (int i = 0; i < stringLength; i++) {
			int number = random.nextInt(base.length());
			randomString.append(base.charAt(number));
		}
		
		return randomString.toString();
	}
}
