package com.socket.tcp.transferObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *  服务器线程处理类
 */
public class ServerThread extends Thread {
	// 和本线程相关的Socket
	Socket socket = null;
	
	public ServerThread( Socket socket) {
		this.socket = socket;
	}
	
	// 线程执行的操作，响应客户端的请求
	@Override
	public void run() {
		InputStream inputStream = null;
		ObjectInputStream objectInput = null;
		try {
			// 获取输入流，读取客户端信息
			inputStream = socket.getInputStream(); // 字节输入流
			// 反序列化对象
			objectInput = new ObjectInputStream(inputStream);
			User user = (User) objectInput.readObject();
			System.out.println("我是服务器，客户端信息为：" + user.getUsername() + ";" + user.getPassword());

			socket.shutdownInput(); // 关闭输入流

			// 4. 响应客户端的请求
			response(socket, user);
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			// 5. 关闭资源
			try {
				if(objectInput != null) {
					objectInput.close();
				}
				if (inputStream != null) {
					inputStream.close();
				}
				if (socket != null) {
					socket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 服务器端响应客户端
	 * @throws IOException 
	 */
	private void response(Socket socket, User user) {
		// 4. 获取输出流，响应客户端的请求
		OutputStream outputStream = null;
		PrintWriter printWriter = null;
		try {
			outputStream = socket.getOutputStream();
			printWriter = new PrintWriter(outputStream);
			printWriter.write("用户" + user.getUsername() + "您好，欢迎您！");
			printWriter.flush();
			socket.shutdownOutput();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭资源
			try {
				if (printWriter != null) {
					printWriter.close();
				}
				if (outputStream != null) {
					outputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
