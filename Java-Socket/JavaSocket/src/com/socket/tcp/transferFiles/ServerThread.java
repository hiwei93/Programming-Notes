package com.socket.tcp.transferFiles;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
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
		BufferedInputStream bufferedInput = null;
		BufferedOutputStream bufferedOutput = null;
		
		String srcPath = System.getProperty("user.dir") + "\\src";
		String fileName = "ClientFile.txt";
		File file = new File(srcPath + "\\" + fileName);
		try {
			// 获取输入流，读取客户端信息
			inputStream = socket.getInputStream(); // 字节输入流
			bufferedInput = new BufferedInputStream(inputStream);
			bufferedOutput = new BufferedOutputStream(new FileOutputStream(file));
			
			byte[] buffer = new byte[1024];
			int len;
			while ((len = bufferedInput.read(buffer)) != -1) {
				bufferedOutput.write(buffer, 0, len);
				bufferedOutput.flush();
			}
			System.out.println("我是服务器，客户端说：" + bufferedOutput);
			
			socket.shutdownInput(); // 关闭输入流

			// 4. 响应客户端的请求
			response(socket);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 5. 关闭资源
			try {
				if(bufferedOutput != null) {
					bufferedOutput.close();
				}
				if (bufferedInput != null){
					bufferedInput.close();
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
	private void response(Socket socket) {
		// 4. 获取输出流，响应客户端的请求
		OutputStream outputStream = null;
		PrintWriter printWriter = null;
		try {
			outputStream = socket.getOutputStream();
			printWriter = new PrintWriter(outputStream);
			printWriter.write("上传成功！");
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
