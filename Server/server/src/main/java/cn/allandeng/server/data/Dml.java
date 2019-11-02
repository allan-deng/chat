/**
 * @Title: Dml.java
 * @Package cn.allandeng.server.data
 * @Description: TODO
 * Copyright: Copyright (c) 2019
 * 
 * @author 邓依伦
 * @date 2019年10月25日 下午8:24:06
 * @version V1.0
 */
package cn.allandeng.server.data;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import cn.allandeng.server.data.DbConnection;;

//本项目数据库中一共有5个表都需要实现增删改
//+----------------------+
//| Tables_in_chatserver |
//+----------------------+
//| chatinfo             |
//| friends              |
//| login                |
//| pwdfind              |
//| userinfo             |
//+----------------------+
/**
  * @ClassName: Dml
  * @Description: 数据库操作语句，更新，插入各种数据
  * @author 邓依伦-Allan
  * @date 2019年10月25日 下午8:24:06
  *
  */
public class Dml {
			
	/**
	  * @Title: insertData
	  * @Description: 向数据库中插入数据
	  * @param @param table
	  * @param @param columns
	  * @param @param values    设定文件
	  * @return void    返回类型
	  * @throws
	  */
	public static void insertData(String table , String columns ,String values) {
		Connection con = null;
		PreparedStatement pre = null;
		
		
		try {
			//创建数据库连接
			con = DbConnection.getConnection();
			//插入字符串
			String sql = "INSERT INTO " + table + "(" + columns + ") VALUES " + "(" + values + ")";
			pre = con.prepareStatement(sql);
			pre.execute();
			//System.out.println("插入成功");
		} catch (Exception e) {
			System.out.println("插入失败");
			e.printStackTrace();
		}
	}
	
	/**
	  * @Title: updateData
	  * @Description: 更新数据
	  * @param     设定文件
	  * @return void    返回类型
	  * @throws
	  */
	public static void updateData(String table ,int No , String columns ,String values) {
		Connection con = null;
		PreparedStatement pre = null;
		String[] column = columns.split(",");
		String[] value = values.split(",");
		String data = "";
		String sql ="";
		if (column.length == value.length) {
			//创建输入修改的数据
			for (int i = 0; i < value.length; i++) {
				data += (column[i] + "=" + value[i]);
				if (i != value.length-1) data += " , ";
			}
			if (table.equals("userinfo")||table.equals("USERINFO")) {
				sql = "UPDATE " + table + " SET " + data + " WHERE UID=" + No;
			} else {
				sql = "UPDATE " + table + " SET " + data +" WHERE NO=" + No;
			}
			//修改数据库
			try {
				//创建数据库连接
				con = DbConnection.getConnection();
				//System.out.println(sql);
				pre = con.prepareStatement(sql);
				pre.execute();
			} catch (Exception e) {
				System.out.println("修改失败");
				e.printStackTrace();
			}
		}else {
			//System.out.println("参数有误！");
		}
	}
	
	/**
	  * @Title: deleteData
	  * @Description: 删除数据
	  * @param @param table
	  * @param @param No    设定文件
	  * @return void    返回类型
	  * @throws
	  */
	public static void deleteData(String table ,int No) {
		Connection con = null;
		PreparedStatement pre = null;
		String sql ="";
		try {
			//创建数据库连接
			con = DbConnection.getConnection();
			//插入字符串
			if (table.equals("userinfo")||table.equals("USERINFO")) {
				sql = "DELETE FROM " + table + " WHERE UID = " + No;
			} else {
				sql = "DELETE FROM " + table + " WHERE NO = " + No;
			}
			
			pre = con.prepareStatement(sql);
			pre.execute();
			//System.out.println("删除成功");
		} catch (Exception e) {
			System.out.println("删除失败");
			e.printStackTrace();
		}
	}

	/**
	  * @Title: delete
	  * @Description: 删除用户
	  * @param @param UID    设定文件
	  * @return void    返回类型
	  * @throws
	  */
	public static void delete(int UID) {
		deleteData("USERINFO" , UID);
	}

	/**
	  * @Title: insert
	  * @Description: 插入用户基本信息
	  * @param @param uid
	  * @param @param pwd
	  * @param @param nickName
	  * @param @param sig
	  * @param @param birthday
	  * @param @param gender    设定文件
	  * @return void    返回类型
	  * @throws
	  */
	public static void insert(int uid , String pwd,String nickName
			,String sig,String birthday,boolean gender) {
		
		insertData("USERINFO", "UID,PWD,NICKNAME,SIG,BIRTHDAY,SEX"
				, Integer.toString(uid) + ",'" + pwd +"','"+ nickName +"','"+ sig+"','"+birthday+"',"
				+Integer.toString((gender)?1:0));
	}
	
	/**
	  * @Title: insert
	  * @Description: 插入用户信息
	  * @param @param uid  用户id
	  * @param @param pwd  用户密码
	  * @param @param nickName    用户昵称
	  * @return void    返回类型
	  * @throws
	  */
	public static void insert(int uid , String pwd,String nickName) {
		insert(uid, pwd, nickName, "", "2000-00-00", false);
	}
	
	public static void main(String[] args) {
		DbConnection.getPropertiseInfo();
		insert(666, "aaa", "aaa", "aaa", "aaa", true);
		updateData("USERINFO" , 666 , "PWD", "'ddd'");
		//delete(666);
	}
}
