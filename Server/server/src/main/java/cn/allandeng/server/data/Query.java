/**
 * @Title: Query.java
 * @Package cn.allandeng.server.data
 * @Description: TODO
 * Copyright: Copyright (c) 2019
 * 
 * @author 邓依伦
 * @date 2019年11月2日 下午9:26:21
 * @version V1.0
 */
package cn.allandeng.server.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.server.data.ConnectionFactory;



/**
  * @ClassName: Query
  * @Description: TODO
  * @author 邓依伦-Allan
  * @date 2019年11月2日 下午9:26:21
  *
  */
public class Query {
	private Connection conn = null;
	private PreparedStatement pre = null;
	private ResultSet rs = null;
	
	/**
	  * 创建一个新的实例 Query. 
	  * <p>Title: </p>
	  * <p>Description: </p>
	  */
	public Query() {
		DbConnection.getPropertiseInfo();
		conn = DbConnection.getConnection();
	}
	

	/**
	  * @Title: querySql
	  * @Description: TODO
	  * @param @param sql
	  * @param @return    设定文件
	  * @return ResultSet   查询结果集
	  * @throws
	  */
	private ResultSet querySql(String sql) {
		try {
			pre = conn.prepareStatement(sql);
			rs = pre.executeQuery();
			return rs;
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("查询语句错误！");
			return null;
		}
		
		
	}
	
	/**
	  * @Title: isExistUser
	  * @Description: 查询用户是否存在
	  * @param @param id
	  * @param @return    设定文件
	  * @return boolean    返回类型
	  * @throws
	  */
	public boolean isExistUser(int id) {
		String sql ="select * from USERINFO where UID = "+id;
		rs = querySql(sql);
		try {
			while(rs.next()){
				return true;
			}
		} catch (SQLException e) {
			System.out.println("数据库查询出错");
			e.printStackTrace();
		} finally {
			DbClose.close(rs, pre, conn);
		}
		return false;
	}
	
	/**
	  * @Title: isValidLoginInfo
	  * @Description: 查询用户登陆信息是否正确
	  * @param @param id
	  * @param @param password
	  * @param @return    设定文件
	  * @return boolean    返回类型
	  * @throws
	  */
	public boolean isValidLoginInfo(int id , String password){
		String sql ="select * from USERINFO where UID = "+id +" and PWD = '"+password+"'" ;
		rs = querySql(sql);
		try {
			while(rs.next()){
				return true;
			}
		} catch (SQLException e) {
			System.out.println("数据库查询出错");
			e.printStackTrace();
		} finally {
			DbClose.close(rs, pre, conn);
		}
		return false;
	}
	
	/**
	  * @Title: getNickname
	  * @Description: 查询用户昵称
	  * @param @param id
	  * @param @return    设定文件
	  * @return String    返回类型
	  * @throws
	  */
	public String getNickname(int id ) {
		String sql ="select NICKNAME from USERINFO where UID = "+id;
		rs = querySql(sql);
		try {
			while(rs.next()){
				return rs.getString("NICKNAME");
			}
		} catch (SQLException e) {
			System.out.println("数据库查询出错");
			e.printStackTrace();
		} finally {
			DbClose.close(rs, pre, conn);
		}
		return null;
	}
	
	public Map<Integer,List<String>> getUsers() {
		String sql ="select UID,PWD,NICKNAME from USERINFO";
		rs = querySql(sql);
		Map<Integer,List<String>> result = new HashMap<Integer, List<String>>();
		try {
			while(rs.next()){
				List<String> pwdNickName = new ArrayList<String>();
				pwdNickName.add(rs.getString(2));pwdNickName.add(rs.getString(3));
				result.put(rs.getInt(1),pwdNickName);
			}
			return result;
		} catch (SQLException e) {
			System.out.println("数据库查询出错");
			e.printStackTrace();
		} finally {
			DbClose.close(rs, pre, conn);
		}
		return null;
	}
	
	/**
	  * @Title: main
	  * @Description: 测试工具类用的main方法
	  * @param @param args    设定文件
	  * @return void    返回类型
	  * @throws
	  */
	public static void main(String[] args) {
		System.out.println(new Query().isExistUser(666));
		System.out.println(new Query().isValidLoginInfo(666, "ddd"));
		System.out.println(new Query().getNickname(666));
	}
}
