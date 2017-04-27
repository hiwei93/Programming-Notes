package com.socket.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * 服务器端，实现基于UDP的用户登录
 */
public class UDPServer {

	public static void main(String[] args) {
		new UDPServer().server();
	}

	public void server() {
		try {
			/**
			 * 服务器端接受客户端发送的数据
			 */
			// 1. 创建服务器端DatagramSocket，指定端口
			DatagramSocket datagramSocket = new DatagramSocket(8800);
			// 2. 创建数据报，用于接受客户端发送的数据
			byte[] data = new byte[1024];
			DatagramPacket datagramPacket = new DatagramPacket(data,
					data.length);
			// 3. 接受客户端发送的数据
			System.out.println("*******服务器即将启动，等待客户端连接********");
			datagramSocket.receive(datagramPacket); // 此方法在接收到数据包之前会阻塞
			// 4. 读取数据
			String info = new String(data, 0, datagramPacket.getLength());
			System.out.println("我是服务器，客户端说：" + info);

			/**
			 * 服务器端向客户端响应数据
			 */
			// 1. 定义客户端的地址，端口号，数据
			InetAddress address = datagramPacket.getAddress();
			int port = datagramPacket.getPort();
			byte[] responseData = "欢迎你！".getBytes();
			// 2. 创建DatagramPacket，包含响应的数据信息
			DatagramPacket responsePacket = new DatagramPacket(responseData,
					responseData.length, address, port);
			// 3. 响应客户端
			datagramSocket.send(responsePacket);

			// 关闭资源
			datagramSocket.close();
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
