package com.socket.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 基于TCP协议的Socket通信，实现用户的登录
 * 服务器端
 */
public class TCPServer {
	public static void main(String[] args) {
		new TCPServer().server(8888);
	}
	
	/**
	 * 接受服务器端的信息
	 * @param port
	 */
	public void server(int port) {
		try {
			// 1. 创建服务器端Socket，即ServerSocket，指定绑定的端口，并监听此端口
			ServerSocket serverSocket = new ServerSocket(port);
			// 2. 调用accept()方法开始监听，等待用户端的连接
			System.out.println("*******服务器即将启动，等待客户端连接********");
			Socket socket = serverSocket.accept();
			// 3. 获取输入流，读取客户端信息
			InputStream inputStream = socket.getInputStream(); // 字节输入流
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream); // 字节流转换为字符流
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader); // 输入流缓冲
			String info = null;
			while ((info = bufferedReader.readLine()) != null ) { // 循环读取客户端的信息
				System.out.println("我是服务器，客户端说：" + info);
			}
			socket.shutdownInput(); // 关闭输入流
			
			//4. 响应客户端的请求
			response(socket);
			
			// 5. 关闭资源
			bufferedReader.close();
			inputStreamReader.close();
			inputStream.close();
			socket.close();
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 服务器端响应客户端
	 * @throws IOException 
	 */
	public void response(Socket socket) throws IOException {
		// 4. 获取输出流，响应客户端的请求
		OutputStream outputStream = socket.getOutputStream(); // 字节输出流
		PrintWriter printWriter = new PrintWriter(outputStream); // 包装为打印流
		printWriter.write("欢迎您！");
		printWriter.flush();
		socket.shutdownOutput();

		// 关闭资源
		printWriter.close();
		outputStream.close();
	}
}