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
	
	private final String soucesDirectory;  // 存放资源文件的文件夹
	private final String arrangeDirectory; // 存放初次整理后文件的文件夹
	private final String resultDirectory;  // 存放result.log的文件夹
	
	public ArrangeWords() {
		soucesDirectory = "resource//sourceSrc//"; 
		arrangeDirectory = "resource//arrangeSrc//";
		resultDirectory = "resource//resultSrc//";
	}
	
	public ArrangeWords(String soucesDirectory) {
		super();
		this.arrangeDirectory = "resource//arrangeSrc//";
		this.resultDirectory = "resource//resultSrc//";
		this.soucesDirectory = soucesDirectory;
	}
	
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
		
		// 文件处理的主要过程
		executeFile(file);
	}
	
	/**
	 * 文件处理的主要过程：
	 * 1. 读取txt文件；
	 * 2. 将txt中的内容提取单词，排序；
	 * 3. 按照单词首字母进行分词，并首次去重；
	 * 4. 将分好的单词存储到本地
	 * @param file
	 * @throws IOException
	 */
	private void executeFile(File file) throws IOException {
		/*
		 * 读取文件，并提取单词：
		 * -  提取单词的javascript正则表达式：/ \s*[,.'"“”()?—:]+\s*[,.'"‘’“”()?—:]*|\s*[0-9]+\.*[0-9]*[,.'"‘’“”()?—:]*\s*|\s+[\[\‘]|[\]\’]\s+|\s+ /
		 * - 注意：文件编码方式必须是UTF-8，不然开头会有一个空字符，怎么都去不掉，影响程序。
		 */
		String seperator = "\\s*[,.;'\"“”()?—:]+\\s*[,.'\"‘’“”()?—:]*|\\s*[0-9]+\\.*[0-9]*[,.;'\"‘’“”()?—:]*\\s*|\\s+[\\[\\‘]|[\\]\\’]\\s+|\\s+";
		String[] words = readFile(file).split(seperator);
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
			outputFile(new File(pathName), entry.getValue().toString());
			
			//System.out.println(entry.getKey() + " have : "  + entry.getValue());
		}
	}
	
	/**
	 * 整合存储到本地的a-z的文件：
	 * 1. 读取存储到本地的a-z的txt文件；
	 * 2. 提取单词，排序；
	 * 3. 再次去重；
	 * 4. 将整理后的单词输出。
	 * @throws IOException 
	 */
	public void arrangeWordsFiles() throws IOException {
		String letter = "abcdefghigklmnopqrstuvwxyz";
		String resultFile = resultDirectory + "result.log";
		String fileName;
		for (int i = 0; i < letter.length(); i++) {
			
			fileName = arrangeDirectory + letter.charAt(i) + ".txt";
			File file = new File(fileName);
			if(file.exists()) {			
				
				// 读取文件，并提取单词
				String seperator = "\\s+";
				String[] words = readFile(file).split(seperator);
				Arrays.sort(words);
				
				// 去重
				StringBuffer buffer = new StringBuffer();
				for(String word : words) {
					if(buffer == null || buffer.indexOf(word) < 0){
						buffer.append(word + " ");
					}
				}
				//System.out.println(buffer);
				
				// 将整理后的单词输出到result.log
				outputFile(new File(resultFile), buffer.toString());
			}
		}		
	}
	
	/**
	 * 输出内容为文件：
	 * 以附加的方式进行输出
	 * @param outputFile
	 * @param content
	 * @throws IOException
	 */
	private void outputFile(File outputFile, String content) throws IOException {
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFile, true));
		bufferedWriter.write(content);
		bufferedWriter.close();
	}
	
	/**
	 * 以字符流的方式读取文件
	 * @param file
	 * @return
	 * @throws IOException
	 */
	private String readFile(File file) throws IOException {
		StringBuffer stringBuffer = new StringBuffer();
		char[] buff = new char[(int) file.length() / 4];
		BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
		while (bufferedReader.read(buff) != -1){
			stringBuffer.append(buff);
		}
		bufferedReader.close();
		
		return stringBuffer.toString().trim();
	}
	
	/**
	 * 初始化运行的环境：
	 * 1. 判断源文件所在文件夹是否存在、是否存在文件；
	 * 2. 创建或清空存放暂存【a-z】文件的文件夹；
	 * 3. 创建或清空存放结果文件的文件夹
	 */
	public void Initialize() {
		File[] fileList;
		
		File soucesFile = new File(soucesDirectory);
		if(!soucesFile.exists()){
			throw new ExceptionFileNotExist("文件夹不存在，初始化失败！");
		}
		
		if(soucesFile.isFile() || !soucesFile.isDirectory()){
			System.out.println("路径不是文件夹，初始化失败！");
			return;
		}
		
		if(soucesFile.listFiles().length <= 0){
			System.out.println("文件夹为空，初始化失败！");
			return;
		}
		
		File arrangeFile = new File(arrangeDirectory);
		if(!arrangeFile.exists()){
			arrangeFile.mkdir();
		}
		
		fileList = arrangeFile.listFiles();
		if(fileList.length > 0) {
			for (File file : fileList) {
				if(!file.delete()){
					System.out.println("无法删除文件：" + file.getName() + "，初识化失败！");
					return;
				}
			}
		}
		
		File resulFile = new File(resultDirectory);
		if(!resulFile.exists()){
			resulFile.mkdir();
		}
		
		fileList = resulFile.listFiles();
		if(fileList.length > 0) {
			for (File file : fileList) {
				if(!file.delete()){
					System.out.println("无法删除文件：" + file.getName() + "，初识化失败！");
					return;
				}
			}
		}
		
		System.out.println("初始化完毕！");		
	}
}
