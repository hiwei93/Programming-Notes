package com.socket.udp.multithread;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ServerThread extends Thread{

	private DatagramSocket datagramSocket;
	private DatagramPacket datagramPacket;
	
	public ServerThread(DatagramSocket datagramSocket,
			DatagramPacket datagramPacket) {
		this.datagramSocket = datagramSocket;
		this.datagramPacket = datagramPacket;
	}
	
	@Override
	public void run() {
		// 4. 读取数据
		String info = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
		System.out.println("我是服务器，客户端说：" + info);
		
		/**
		 * 服务器端向客户端响应数据
		 */
		try {
			// 1. 定义客户端的地址，端口号，数据
			InetAddress address = datagramPacket.getAddress();
			int port = datagramPacket.getPort();
			byte[] responseData = "欢迎你！".getBytes();
			// 2. 创建DatagramPacket，包含相应的数据信息
			DatagramPacket responsePacket = new DatagramPacket(responseData, responseData.length, address, port);
			// 3. 相应客户端
			datagramSocket.send(responsePacket);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
