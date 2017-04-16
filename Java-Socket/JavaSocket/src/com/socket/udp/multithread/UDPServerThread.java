package com.socket.udp.multithread;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * 服务器端，实现基于UDP的用户登录
 */
public class UDPServerThread {
	
	public static void main(String[] args) {
		new UDPServerThread().server();
	}

	public void server() {
		try {
			/**
			 * 服务器端接受客户端发送的数据
			 */
			// 1. 创建服务器端DatagramSocket，指定端口
			DatagramSocket datagramSocket = new DatagramSocket(8800);
			byte[] data = new byte[1024];
			System.out.println("*******服务器即将启动，等待客户端连接********");
			while (true) {
				// 2. 创建数据报，用于接受客户端发送的数据
				DatagramPacket datagramPacket = new DatagramPacket(data, data.length);
				//3. 接受客户端发送的数据
				datagramSocket.receive(datagramPacket);  // 此方法在接收到数据包之前会阻塞
				new ServerThread(datagramSocket, datagramPacket).start();;
			}
			
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
