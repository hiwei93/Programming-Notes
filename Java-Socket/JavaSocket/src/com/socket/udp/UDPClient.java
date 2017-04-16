package com.socket.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * 客户端，实现基于UDP的用户登录
 */
public class UDPClient {
	public static void main(String[] args) {
		new UDPClient().client();
	}
	
	public void client() {
		try {
			/**
			 * 客户端向服务器端发送数据
			 */
			// 1. 定义服务器的地址、端口号、数据
			InetAddress address = InetAddress.getByName("localhost");
			int port = 8800;
			byte[] data = "用户名：admin;密码：123".getBytes();
			// 2. 创建数据报，包含发送的数据信息
			DatagramPacket datagramPacket = new DatagramPacket(data, data.length, address, port);
			// 3. 创建DatagramSocket对象
			DatagramSocket datagramSocket = new DatagramSocket();
			// 4. 箱服务器端发送数据报
			datagramSocket.send(datagramPacket);
			
			/**
			 * 客户端接受服务器相应消息
			 */
			// 1. 创建数据报，用与接收服务器响应消息
			byte[] receiveData = new byte[1024];
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			// 2. 接受服务器端响应的数据
			datagramSocket.receive(receivePacket);
			// 3. 读取数据
			String reply = new String(receiveData, 0, receivePacket.getLength());
			System.out.println("我是客户端，服务器说：" + reply);
			
			// 关闭资源
			datagramSocket.close();
		} catch (UnknownHostException | SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
