/**
 * @Title: DbConnection.java
 * @Package cn.allandeng.server.data
 * @Description: TODO
 * Copyright: Copyright (c) 2019
 * 
 * @author 邓依伦
 * @date 2019年10月25日 下午7:33:59
 * @version V1.0
 */
package cn.allandeng.server.data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.jdbc.exceptions.MySQLSyntaxErrorException;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
  * @ClassName: DbConnection
  * @Description: 连接数据库
  * @author 邓依伦-Allan
  * @date 2019年10月25日 下午7:33:59
  *
  */
public class DbConnection {
	
	//数据库驱动名
	private static String DRIVER = "";
	//数据库URL
	private static String URL = "";
	//数据库用户名
	private static String USERNAME = "";
	//数据库密码
	private static String PASSWORD = "";
	
	/**
	  * @Title: getPropertiseInfo
	  * @Description: 获取数据库配置信息
	  * @param     设定文件
	  * @return void    返回类型
	  * @throws
	  */
	public static void getPropertiseInfo() {
		Properties prop = new Properties();
		//加载jdbc的配置
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new FileReader("config/jdbc.properties"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//载入配置文件
		try {
			prop.load(bufferedReader);
		} catch (Exception e) {
			// TODO: handle exception
//			Alert alert = new Alert(AlertType.ERROR);
//			alert.setTitle("错误");
//			alert.setContentText("找不到或无法打开数据库配置文件！");
			e.printStackTrace();
		}
		
		//将配置文件写入变量
		DRIVER = prop.getProperty("driver");
		URL = prop.getProperty("url");
		USERNAME = prop.getProperty("username");
		PASSWORD = prop.getProperty("password");
	
	} 
	
	/**
	  * @Title: getConnection
	  * @Description: 获取数据库连接
	  * @param @return    设定文件
	  * @return Connection    返回类型
	  * @throws
	  */
	public static Connection getConnection() {
		Connection con = null;
		try {
			//加载数据库驱动
			//Class.forName(name) 返回一个名为name的类，同时也加载了这个类
			//System.out.println(DRIVER);
			Class.forName(DRIVER);
		} catch (Exception e) {
			
//			Alert alert = new Alert(AlertType.ERROR);
//			alert.setTitle("错误");
//			alert.setContentText("数据库驱动加载错误！");
			System.out.println("数据库驱动加载错误！");
			e.printStackTrace();
		}
		try {
			//获得数据库连接对象
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			//System.out.println("数据库连接成功！");
//			Alert alert = new Alert(AlertType.INFORMATION);
//			alert.setTitle("提示");
//			alert.setContentText("数据库连接成功！");
		} catch (MySQLSyntaxErrorException e) {
			
//			Alert alert = new Alert(AlertType.ERROR);
//			alert.setTitle("错误");
//			alert.setContentText("数据库连接失败！");
			System.out.println("数据库连接失败！ 未找到数据库");
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("数据库连接失败！" );
		}
		return con;
	}
	
	/**
	  * @Title: main
	  * @Description: 测试用主方法
	  * @param @param args    设定文件
	  * @return void    返回类型
	  * @throws
	  */
	public static void main(String[] args) {
		getPropertiseInfo();
		getConnection();
	}
}
