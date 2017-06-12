package com.interview.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ArrangeWords {
	
	/**
	 * Description : 
	 * 1. 读取一个文件夹内所有的txt文件，该文件夹的深度未知，且TXT文件的大小不定；
	 * 2. 将txt文件中的单词读取出来，以空格为分隔符，按照a-z排序；
	 * 3. 将排序好的单词写到result.log文件中，单词以空格为分隔符。
	 */
	
//	private final String soucesDirectory = "resource//sourceSrc//";  // 存放资源文件的文件夹
	private final String arrangeDirectory = "resource//arrangeSrc//"; // 存放初次整理后文件的文件夹
	private final String resultDirectory = "resource//resultSrc//";  // 存放result.log的文件夹
	
	/**
	 * solve：
	 * 1. 读取当前文件夹中所有的文件，按照空格为分隔符，按照首字母将其写入a-z的文件中；
	 * 2. 读取完所有的txt文件之后，依次读取a-z的文件，然后排序，最后写入到result.log中。
	 * @throws IOException 
	 */	
	public void arrange(File file) throws IOException {
		// 1. 文件不存在抛出异常
		if(!file.exists()){
			throw new ExceptionFileNotExist("文件不存在！");
		}
		
		// 2. 如果是文件夹则处理文件夹
		if(file.isDirectory()){
			dealWithDirectory(file);
		}
		
		// 3. 如果是文件则处理文件
		if(file.isFile()){
			dealWithFile(file);
		}
	}
	
	/**
	 * 处理文件夹
	 * @param parentFile
	 * @throws IOException 
	 */
	private void dealWithDirectory(File parentFile) throws IOException {
		File[] files = parentFile.listFiles();
		if(files.length > 0){
			for (File file : files) {
				arrange(file);
			}			
		} else {
			System.out.println("Directory : " + parentFile.getName() + "is empty!");
		}
	}
	
	/**
	 * 处理文件
	 * @param file
	 * @throws IOException 
	 */
	private void dealWithFile(File file) throws IOException {
		String fileName = file.getName();
		if(!fileName.endsWith(".txt")){
			System.out.println("File : " + fileName + " is not type of txt!");
			return;
		}
		
		// 文件主要处理过程
		executeFile(file);
	}
	
	/**
	 * 读取文件并分词、去重；
	 * 将分好的单词按周字母分组，存储到本地
	 * @param file
	 * @throws IOException
	 */
	private void executeFile(File file) throws IOException {
		// 字符流的方式读文件
		BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
		char[] buff = new char[(int) file.length() / 4];
		StringBuffer stringBuffer = new StringBuffer();
		while (bufferedReader.read(buff) != -1){
			stringBuffer.append(buff);
		}
		bufferedReader.close();
		
		// 提取单词的正则表达式
		/**
		 * 注意文件编码方式必须是UTF-8，不然开头会有一个空字符，怎么都去不掉，影响程序。
		 */
		// javascript：/ \s*[,.'"“”()?—:]+\s*[,.'"‘’“”()?—:]*|\s*[0-9]+\.*[0-9]*[,.'"‘’“”()?—:]*\s*|\s+[\[\‘]|[\]\’]\s+|\s+ /
		String seperator = "\\s*[,.;'\"“”()?—:]+\\s*[,.'\"‘’“”()?—:]*|\\s*[0-9]+\\.*[0-9]*[,.;'\"‘’“”()?—:]*\\s*|\\s+[\\[\\‘]|[\\]\\’]\\s+|\\s+";
		String[] words = stringBuffer.toString().trim().split(seperator);
		Arrays.sort(words);
		
		// 处理单词
		Map<String, StringBuffer> wordsMap = new HashMap<>();
		String firstLetter;
		for (String word : words) {
			firstLetter = word.substring(0, 1).toLowerCase();
			// 按首字母分词
			if(!wordsMap.containsKey(firstLetter)) {
				wordsMap.put(firstLetter, new StringBuffer());
			}
			
			// 去重
			StringBuffer buffer = wordsMap.get(firstLetter);
			if(buffer == null || buffer.indexOf(word) < 0) {
				buffer.append(word.toLowerCase() + " ");
				wordsMap.put(firstLetter, buffer);
			}
		}
		
		// 按首字母分好的单词先分类储存
		String pathName;
		for(Map.Entry<String, StringBuffer> entry : wordsMap.entrySet()){
			pathName = arrangeDirectory + entry.getKey() + ".txt";
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(pathName), true));
			bufferedWriter.write(entry.getValue().toString());
			
			bufferedWriter.close();
//			System.out.println(entry.getKey() + " have : "  + entry.getValue());
		}
	}
	
	/**
	 * 读取存储到本地的a-z的文件；
	 * 进行排序，去重后，写入到最后的
	 * @throws IOException 
	 */
	public void rangeWordsFiles() throws IOException {
		String letter = "abcdefghigklmnopqrstuvwxyz";
		String resultFile = resultDirectory + "result.log";
		String fileName;
		for (int i = 0; i < letter.length(); i++) {
			fileName = arrangeDirectory + letter.charAt(i) + ".txt";
			File file = new File(fileName);
			if(file.exists()) {				
				// 字符流的方式读文件
				BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
				char[] buff = new char[(int) file.length() / 4];
				StringBuffer stringBuffer = new StringBuffer();
				while (bufferedReader.read(buff) != -1){
					stringBuffer.append(buff);
				}
				bufferedReader.close();
				
				String seperator = "\\s+";
				String[] words = stringBuffer.toString().trim().split(seperator);
				Arrays.sort(words);
				
				StringBuffer buffer = new StringBuffer();
				for(String word : words) {
					if(buffer == null || buffer.indexOf(word) < 0){
						buffer.append(word + " ");
					}
				}
				System.out.println(buffer);
				
				BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(resultFile), true));
				bufferedWriter.write(buffer.toString());
				bufferedWriter.close();
			}
		}		
	}
}
