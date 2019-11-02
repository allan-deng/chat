/**
 * @Title: UserDetailInfo.java
 * @Package cn.allandeng.common
 * @Description: TODO
 * Copyright: Copyright (c) 2019
 * 
 * @author 邓依伦
 * @date 2019年11月2日 下午2:56:52
 * @version V1.0
 */
package cn.allandeng.common;

import java.io.Serializable;

/**
  * @ClassName: UserDetailInfo
  * @Description: TODO
  * @author 邓依伦-Allan
  * @date 2019年11月2日 下午2:56:52
  *
  */
public class UserDetailInfo extends UserInfo {
	private String password;
	/**
	 * setter method
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * getter method
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
}
