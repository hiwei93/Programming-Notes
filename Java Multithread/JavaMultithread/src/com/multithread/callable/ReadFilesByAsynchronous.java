package com.multithread.callable;

import java.io.File;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 异步的方式读取文件
 */
public class ReadFilesByAsynchronous {
	
	public static void main(String[] args) {
		File file = new File("e://1.txt");
		
		if(!file.exists() | !file.isFile()) {
			System.err.println("路径有误");
			return;
		}
		
		ExecutorService executorService = Executors.newCachedThreadPool();
		Future<StringBuffer> future = executorService.submit(new FileReader(file));
		
		System.out.println("#####文件正在读取，还可以做其他的事情哎######！");
		
		while (!future.isDone()){
			System.out.println("还没读完-_-||||");
		}
		
		try {
			System.out.println("读完啦，终于可以显示了：");
			System.err.println(future.get().toString());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
		executorService.shutdown();
	}

}
