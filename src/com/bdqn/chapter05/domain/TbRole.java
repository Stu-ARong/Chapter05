package com.bdqn.chapter05.domain;

import java.util.Date;

/**
 * @description 角色实体类
 * @author Administrator
 * @date 2019-10-10
 * @version 1.0
 * @since JDK1.7
 */
public class TbRole {

	private Integer roleId;
	private String roleName;
	private Date createDate;
	private Double initMoney;

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Double getInitMoney() {
		return initMoney;
	}

	public void setInitMoney(Double initMoney) {
		this.initMoney = initMoney;
	}

}
