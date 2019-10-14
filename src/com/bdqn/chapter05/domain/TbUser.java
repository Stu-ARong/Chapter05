package com.bdqn.chapter05.domain;

/**
 * @description 用户实体类
 * @author Administrator
 * @date 2019-9-21
 * @version 1.0
 * @since JDK1.7
 */
public class TbUser {

	private String userName;
	private String pwd;
	private String messageSrc;
	private String pic;
	private int roleId;

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public TbUser(String userName, String pwd, String messageSrc) {
		this.userName = userName;
		this.pwd = pwd;
		this.messageSrc = messageSrc;
	}

	public TbUser() {
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getMessageSrc() {
		return messageSrc;
	}

	public void setMessageSrc(String messageSrc) {
		this.messageSrc = messageSrc;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}
}
