package com.multithread.callable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.concurrent.Callable;

/**
 * 读取文件
 * @author wei11
 *
 */
public class FileReader implements Callable<StringBuffer>{
	
	private File file;
	
	public FileReader(File file) {
		super();
		this.file = file;
	}

	@Override
	public StringBuffer call() throws Exception {
		StringBuffer buffer = new StringBuffer();
		
		synchronized (FileReader.class) {
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String line;
			while ((line = bufferedReader.readLine()) != null){
				buffer.append(line + "\n");
			}
			
			bufferedReader.close();
		}
		return buffer;
	}

}
