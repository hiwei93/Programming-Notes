package com.socket.tcp.transferObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
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
		System.out.println("***********请登录************");
		System.out.println("输入用户名：");
		Scanner input = new Scanner(System.in);
		String username = input.next();
		System.out.println("输入密码：");
		String password = input.next();
		User user = new User(username, password);
		
		try {
			// 1. 创建客户端Socket，指定服务器地址和端口
			Socket socket = new Socket(host, port);
			// 2. 获取输出流，向服务器端发送信息
			OutputStream outputStream = socket.getOutputStream(); // 字节输出流
			// 序列化对象
			ObjectOutputStream objectOutput = new ObjectOutputStream(outputStream);
			objectOutput.writeObject(user);
			objectOutput.flush();
			socket.shutdownOutput(); // 关闭输出流
			
			// 3. 获取输入流，读取服务器端的响应信息
			responseInfo(socket);
			
			// 4. 关闭资源
			objectOutput.close();
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
