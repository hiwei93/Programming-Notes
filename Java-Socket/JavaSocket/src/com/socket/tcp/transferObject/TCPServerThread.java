package com.socket.tcp.transferObject;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 基于TCP协议的Socket通信，实现对象的传输
 * 服务器端
 */
public class TCPServerThread {
	public static void main(String[] args) {
		new TCPServerThread().server(8888);
	}
	
	/**
	 * 接受服务器端的信息
	 * @param port
	 */
	public void server(int port) {
		try {
			// 1. 创建服务器端Socket，即ServerSocket，指定绑定的端口，并监听此端口
			ServerSocket serverSocket = new ServerSocket(port);
			Socket socket = null;
			int count = 0; // 记录客户端的数量
			System.out.println("*******服务器即将启动，等待客户端连接********");
			while (true) {
				// 2. 调用accept()方法开始监听，等待用户端的连接
				socket = serverSocket.accept();
				// 3. 创建线程与客户端进行通信
				ServerThread serverThread = new ServerThread(socket);
				serverThread.setPriority(4);
				// 4. 启动线程
				serverThread.start();
				
				count++; // 统计客户端的数量
				System.out.println("客户端的数量：" + count);
				
				// 获取当前客户端的IP
				InetAddress address = socket.getInetAddress();
				System.out.println("当期那客户端的IP：" + address.getHostAddress());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}