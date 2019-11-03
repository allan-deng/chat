/**
· * @Title: ConsoleInput.java
 * @Package cn.allandeng.server
 * @Description: TODO
 * Copyright: Copyright (c) 2019
 * 
 * @author 邓依伦
 * @date 2019年11月2日 下午1:19:54
 * @version V1.0
 */
package cn.allandeng.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.allandeng.server.data.Dml;
import cn.allandeng.server.data.Query;
import cn.allandeng.server.model.ClientsMap;

/**
  * @ClassName: ConsoleInput
  * @Description: 响应控制台输入
  * @author 邓依伦-Allan
  * @date 2019年11月2日 下午1:19:54
  *
  */
public class ConsoleInput extends Thread {
	//包装system.in
	private InputStream in = new FilterInputStream(System.in){
        @Override
        public void close() throws IOException {
            //do nothing
        }
	};
	//键盘输入流
	private BufferedReader keyin = new BufferedReader(new InputStreamReader(in));
	//文件输入输出流
	
	public void respondInput() {
		String command = "";
		
		try {
			//死循环响应命令行输入
			while((command = keyin.readLine()) != null) {
				if (command.length()>0) {
					switch (command.split(" ")[0]) {
					case "help":
						help();
						break;
					case "about":
						about();
						break;
					case "online":
						//显示所有的在线用户
						online(command);
						break;
					case "displayon":
						//显示相互发送的信息
						GlobalVariable.showChatMassage = true ;
						System.out.println("开启消息显示");
						break;
					case "displayoff":
						//不显示相互发送的信息
						GlobalVariable.showChatMassage = false ;
						System.out.println("关闭消息显示");
						break;	
					case "adduser":
						//向数据库中添加用户
						addUser(command);
						break;
					case "showusers":
						//显示数据库中所有用户
						showUsers();
						break;
					case "changepassword":
						//修改密码
						changePassword(command);
						break;
					case "changenickname":
						//修改用户名
						changeNickName(command);
						break;
					default:
						System.out.println("输入指令有误！使用help命令查看帮助");
						break;
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	/**
	  * @Title: chageNickName
	  * @Description: TODO
	  * @param @param command    设定文件
	  * @return void    返回类型
	  * @throws
	  */
	private void changeNickName(String command) {
		Map<Integer,List<String>> map = new Query().getUsers();
		String[] s =command.split(" ");
		int uid = Integer.parseInt(s[1]);
		String nickName = s[2];
		if (map.keySet().contains(uid)) {
			Dml.updataNickName(uid, nickName);
			System.out.println("已修改昵称，ID:" + uid +" 昵称："+nickName);
		}else {
			System.out.println("用户不存在！");
		}
		
	}
	/**
	  * @Title: changePassword
	  * @Description: TODO
	  * @param @param command    设定文件
	  * @return void    返回类型
	  * @throws
	  */
	private void changePassword(String command) {
		Map<Integer,List<String>> map = new Query().getUsers();
		String[] s =command.split(" ");
		int uid = Integer.parseInt(s[1]);
		String password = s[2];
		if (map.keySet().contains(uid)) {
			Dml.updataPassword(uid, password);
			System.out.println("已修改密码，ID:" + uid +" 密码："+password);
		}else {
			System.out.println("用户不存在！");
		}
		
	}
	/**
	  * @Title: showUsers
	  * @Description: 显示所有用户
	  * @param     设定文件
	  * @return void    返回类型
	  * @throws
	  */
	private void showUsers() {
		Map<Integer,List<String>> map = new Query().getUsers();
		System.out.println("----所有用户列表----");
		for (Integer k:map.keySet()) {
			System.out.println("用户ID:" + k + " 昵称:" + map.get(k).get(1) +" 密码:" + map.get(k).get(0) );
		}
		
	}
	/**
	  * @Title: addUser
	  * @Description: 添加新用户
	  * @param @param command    设定文件
	  * @return void    返回类型
	  * @throws
	  */
	private void addUser(String command) {
		Map<Integer,List<String>> map = new Query().getUsers();
		String[] s =command.split(" ");
		int uid = Integer.parseInt(s[1]);
		String password = s[2];
		String nickName = s[3];
		if (!map.keySet().contains(uid)) {
			Dml.insert(uid, password, nickName);
			System.out.println("添加新用户，ID:" + uid +" 密码："+password+" 昵称："+nickName);
		}else {
			System.out.println("用户已存在！");
		}
		
	}
	/**
	  * @Title: online
	  * @Description: TODO
	  * @param @param command    设定文件
	  * @return void    返回类型
	  * @throws
	  */
	private void online(String command) {
		Set<Integer> clientsID = CreateSocket.getClientsID();
		ClientsMap<Integer, String> nickname = CreateSocket.getUserNicknames();
		System.out.println("当前在线用户数：" + clientsID.size());
		int i = 1  ;
		for(Integer id:clientsID) {
			System.out.println("第"+i+"个用户    ID:" + id+"   昵称："+nickname.map.get(id));
			i++;
		}
		
	}
	/**
	  * @Title: about
	  * @Description: 显示关于信息
	  * @param     设定文件
	  * @return void    返回类型
	  * @throws
	  */
	private void about() {
		displayFile("config/about.txt");
	}
	/**
	  * @Title: help
	  * @Description: 显示帮助信息
	  * @param     设定文件
	  * @return void    返回类型
	  * @throws
	  */
	private void help() {
		displayFile("config/help.txt");
		
	}
	
	private void displayFile(String fileAddress) {
		String encoding = "utf8";
		String file = fileAddress;
		
		try {
			BufferedReader fr = new BufferedReader(new InputStreamReader(new FileInputStream(new File(file)) , encoding));
			String lineTxt = "";
			while ((lineTxt = fr.readLine()) != null)
            {
                System.out.println(lineTxt);
            }
			fr.close();
						
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("找不到文件或无法打开文件");
		}
	}
	
	
	/*
	  * <p>Title: run</p>
	  * <p>Description: </p>
	  * @see java.lang.Thread#run()
	  */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		respondInput();
	}
}
