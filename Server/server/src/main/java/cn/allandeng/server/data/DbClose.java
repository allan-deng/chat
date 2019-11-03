/**
 * @Title: DbClose.java
 * @Package cn.allandeng.server.data
 * @Description: TODO
 * Copyright: Copyright (c) 2019
 * 
 * @author 邓依伦
 * @date 2019年10月25日 下午7:34:48
 * @version V1.0
 */
package cn.allandeng.server.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
  * @ClassName: DbClose
  * @Description: 关闭数据库连接
  * @author 邓依伦-Allan
  * @date 2019年10月25日 下午7:34:48
  *
  */
public class DbClose {
	
	/**
	 * 关闭数据库连接
	 * @param conn 连接类对象
	 */
	public static void close(Connection conn){
		if(null != conn){
			try{
				conn.close();
				//System.out.println("数据库连接关闭");
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 关闭数据库语句
	 * @param stmt 语句对象
	 */
	public static void close(PreparedStatement pre){
		if(null != pre){
			try{
				pre.close();
				//System.out.println("查询语句关闭");
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
	}
	
	/**
	 *关闭数据库结果集
	 * @param rs 结果集对象
	 */
	public static void close(ResultSet rs){
		if(null != rs){
			try{
				rs.close();
				//System.out.println("结果集关闭");
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
	}
	
	public static void close(ResultSet rs,PreparedStatement pre,Connection conn){
		close(rs);
		close(pre,conn);
	}
	public static void close(PreparedStatement pre,Connection conn){
		close(pre);
		close(conn);
	}


}
