package com.interview.string;

public class RetreiveString {
	
	/**
	 * 1. 有字符串a、b；
	 * 2. 在字符串a中找到b字符串，且将b首字母在a字符创中的索引位置返回；
	 * 3. 如果没找到则返回-1，b为空字符串则返回0；
	 */
	
	public int findStringIndex(String main, String find) {
		
		// 输入检测
		if(main == null || find == null){
			throw new NullPointerException();
		}
		
		// 特殊值的检测
		if("".equals(find)){
			return 0;
		}

		int position = 0; // 存储匹配字符的索引
		int offset = 0; // 存储偏移量
		boolean isFind = true;

		for (int i = 0; i < find.length() && isFind; i++) {
			for (int j = position; j < main.length(); j++) {
				if (find.charAt(i) == main.charAt(j)) {
					offset++;
					position = j;
					isFind = true;
					break;
				} else {
					isFind = false;
				}
			}
		}
		return offset == find.length() ? position - offset + 1 : -1;
	}
}
