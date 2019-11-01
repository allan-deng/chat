/**
 * @Title: sampleServer.java
 * @Package cn.allandeng.server
 * @Description: TODO
 * Copyright: Copyright (c) 2019
 * 
 * @author 邓依伦
 * @date 2019年11月1日 下午2:51:27
 * @version V1.0
 */
package cn.allandeng.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import cn.allandeng.common.Massage;

/**
  * @ClassName: sampleServer
  * @Description: TODO
  * @author 邓依伦-Allan
  * @date 2019年11月1日 下午2:51:27
  *
  */
public class sampleServer extends Thread{
	private Socket socket ;
	ObjectInputStream ois = null;
	ObjectOutputStream oos = null;
	
	
	//构造器 用来获取Socket对象
	/**
	  * 创建一个新的实例 ServerThread. 
	  */
	public sampleServer(Socket socket) {
		this.socket = socket ;
		System.out.println("已启动一个服务线程");
		try {
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
			System.out.println("创建输入输出流完成");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	

	@Override
	public void run() {
		Massage buffer = null;
		try {
			//System.out.println("接收之前");
			buffer = (Massage)ois.readObject();
			System.out.println("接收之后");
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(buffer + buffer.getText());
	}
}
