package com.socket.tcp.transferFiles;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * 基于TCP协议的Socket通信，实现用户的登录
 * 客户端
 */
public class TCPClientThread {
	public static void main(String[] args) {
		new TCPClientThread().client("localhost", 8888);
	}
	
	public void client(String host, int port) {
		
		Scanner input = new Scanner(System.in);
		System.out.println("请指定上传文件：");
		String path = input.next().trim().replace("/|\\", "//");
		File file =  new File(path);;
		System.out.println(file.exists());
		while (!file.exists()) {
			System.out.println("文件不存在，请输指定其他文件：");
			path = input.next().trim().replace("/|\\", "//");
			file = new File(path);
		}
		
		try {
			// 1. 创建客户端Socket，指定服务器地址和端口
			Socket socket = new Socket(host, port);
			// 2. 获取输出流，向服务器端发送信息
			OutputStream outputStream = socket.getOutputStream(); // 字节输出流
			BufferedInputStream bufferInput = new BufferedInputStream(new FileInputStream(file));
			BufferedOutputStream bufferedOutput = new BufferedOutputStream(outputStream);
			
			byte[] buffer = new byte[1024];
			int len;
			while ((len = bufferInput.read(buffer)) != -1) {
				bufferedOutput.write(buffer, 0, len);
				bufferedOutput.flush();
			}
			socket.shutdownOutput(); // 关闭输出流
			
			// 3. 获取输入流，读取服务器端的响应信息
			responseInfo(socket);
			
			// 4. 关闭资源
			bufferedOutput.close();
			bufferInput.close();
			outputStream.close();
			socket.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void responseInfo(Socket socket) throws IOException {
		// 3. 获取输入流，读取服务器端的响应信息
		InputStream inputStream = socket.getInputStream();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		String info = null;
		while ((info = bufferedReader.readLine()) != null ) { // 循环读取客户端的信息
			System.out.println("我是客户端，服务器说：" + info);
		}
		socket.shutdownInput(); // 关闭输入流
		
		// 关闭资源
		bufferedReader.close();
		inputStream.close();
	}

}
