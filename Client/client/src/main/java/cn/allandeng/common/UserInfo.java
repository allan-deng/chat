/**
 * @Title: UserInfo.java
 * @Package cn.allandeng.common
 * @Description: TODO
 * Copyright: Copyright (c) 2019
 * 
 * @author 邓依伦
 * @date 2019年11月2日 下午2:56:24
 * @version V1.0
 */
package cn.allandeng.common;

import java.io.Serializable;

/**
  * @ClassName: UserInfo
  * @Description: 用户信息 仅仅存放用户昵称
  * @author 邓依伦-Allan
  * @date 2019年11月2日 下午2:56:24
  *
  */
public class UserInfo implements Serializable{
	/**
	  * @Fields serialVersionUID : TODO（用一句话描述这个变量表示什么）
	  */
	
	private static final long serialVersionUID = 1L;
	private String nickName;
	
	/**
	  * 创建一个新的实例 UserInfo. 
	  * <p>Title: </p>
	  * <p>Description: </p>
	  */
	public UserInfo(String nickName) {
		this.nickName = nickName;
	}
	
	public UserInfo() {
		this.nickName = "";
	}
	
	/**
	 * getter method
	 * @return the nickName
	 */
	public String getNickName() {
		return nickName;
	}
	
	/**
	 * setter method
	 * @param nickName the nickName to set
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
}
