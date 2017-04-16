package com.socket.tcp.multithread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferedReader = null;
		try {
			// 获取输入流，读取客户端信息
			inputStream = socket.getInputStream(); // 字节输入流
			inputStreamReader = new InputStreamReader(
					inputStream); // 字节流转换为字符流
			bufferedReader = new BufferedReader(
					inputStreamReader); // 输入流缓冲
			String info = null;
			while ((info = bufferedReader.readLine()) != null) { // 循环读取客户端的信息
				System.out.println("我是服务器，客户端说：" + info);
			}
			socket.shutdownInput(); // 关闭输入流

			// 4. 响应客户端的请求
			response(socket);

			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 5. 关闭资源
			try {
				if(bufferedReader != null) {
					bufferedReader.close();
				}
				if (inputStreamReader != null){
					inputStreamReader.close();
				}
				if (inputStream != null) {
					inputStream.close();
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
	private void response(Socket socket) {
		// 4. 获取输出流，响应客户端的请求
		OutputStream outputStream = null;
		PrintWriter printWriter = null;
		try {
			outputStream = socket.getOutputStream();
			printWriter = new PrintWriter(outputStream);
			printWriter.write("欢迎您！");
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
