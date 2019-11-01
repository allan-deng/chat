/**
 * @Title: CreateSocket.java
 * @Package cn.allandeng.server
 * @Description: TODO
 * Copyright: Copyright (c) 2019
 * 
 * @author 邓依伦
 * @date 2019年10月31日 下午1:09:55
 * @version V1.0
 */
package cn.allandeng.server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import cn.allandeng.common.Massage;
import cn.allandeng.server.model.ClientsMap;

/**
  * @ClassName: CreateSocket
  * @Description: 接受来自客户端的请求并创建新的进程处理这些请求
  * @author 邓依伦-Allan
  * @date 2019年10月31日 下午1:09:55
  *
  */
public class CreateSocket extends Thread {
	//监听端口号
	private static final int SERVER_PORT= 6666 ;
	//在线用户列表
	public static ClientsMap<Integer, ObjectOutputStream> clients = new ClientsMap<>() ;
	public void init() {
		try (
				ServerSocket ss = new ServerSocket(SERVER_PORT); //建立监听ServerSocket
			)
		{
			//死循环等等待客户机连接
			System.out.println("开始等待连接");
			while (true) {
				//客户机连接后创建新的ServerThread线程处理这个连接
				Socket socket = ss.accept();
				System.out.println("已连接上一台客户机");
				
//				ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
//				System.out.println("创建输入输出流完成");
//				Massage buffer = (Massage)ois.readObject();
//				System.out.println(buffer + buffer.getText());
//				
//				new sampleServer(socket).start();
				new ServerThread(socket).start();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("服务启动失败，是否" + SERVER_PORT + "端口被占用？");
		}
	}
	
	/*
	  * <p>Title: run</p>
	  * <p>Description: </p>
	  * @see java.lang.Thread#run()
	  */
	
	@Override
	public void run() {
		init();
		super.run();
	}
}
