package com.iokays.usertorole.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.iokays.role.domain.Role;
import com.iokays.user.domain.User;
import com.iokays.utils.domain.IdEntity;
import com.iokays.utils.domain.Status;

/**
 * 用户角色关联表
 * 
 * @author pengyuanbing@gmail.com
 *
 */
@Entity
@Table(name = "t_pub_user_role", uniqueConstraints = {@UniqueConstraint(columnNames={"user_id_", "role_id_"})})
public class UserToRole extends IdEntity implements Serializable {

	private static final long serialVersionUID = 2693663154227947127L;
	
	private User user;					//用户
	private Role role;					//角色
	private Status status;				//是否可用
	
	public UserToRole() {};
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id_", nullable = true)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id_", nullable = true)
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name ="status_", nullable = true)
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
}
