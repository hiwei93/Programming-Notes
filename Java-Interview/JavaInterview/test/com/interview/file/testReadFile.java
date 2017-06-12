package com.interview.file;

import java.io.File;
import java.io.IOException;

public class testReadFile {

	public static void main(String[] args) throws IOException {
		
		ArrangeWords arrangeWords = new ArrangeWords();
		String soucesDirectory = "resource//sourceSrc";
		
		arrangeWords.Initialize();
		
		File file = new File(soucesDirectory);
		if(file.exists() && file.isDirectory()){
			arrangeWords.arrange(file);
			arrangeWords.arrangeWordsFiles();
		}
	}

}
