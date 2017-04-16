package com.socket.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 基于TCP协议的Socket通信，实现用户的登录
 * 客户端
 */
public class TCPClient {
	public static void main(String[] args) {
		new TCPClient().client("localhost", 8888);
	}
	
	public void client(String host, int port) {
		try {
			// 1. 创建客户端Socket，指定服务器地址和端口
			Socket socket = new Socket(host, port);
			// 2. 获取输出流，向服务器端发送信息
			OutputStream outputStream = socket.getOutputStream(); // 字节输出流
			PrintWriter printWriter = new PrintWriter(outputStream); // 字节输出流转换为字符输出流
			printWriter.write("用户名：admin;密码：123");
			printWriter.flush();
			socket.shutdownOutput(); // 关闭输出流
			
			// 3. 获取输入流，读取服务器端的响应信息
			responseInfo(socket);
			
			// 4. 关闭资源
			printWriter.close();
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
