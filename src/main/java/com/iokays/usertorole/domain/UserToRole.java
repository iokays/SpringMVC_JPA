package com.iokays.usertorole.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.iokays.role.domain.Role;
import com.iokays.user.domain.User;
import com.iokays.utils.domain.IdEntity;

/**
 * 用户角色关联表
 * 
 * @author pengyuanbing@gmail.com
 *
 */
@Entity
@Table(name = "t_pub_user_role")
public class UserToRole extends IdEntity implements Serializable{

	private static final long serialVersionUID = 2693663154227947127L;
	
	private User user;					//用户
	private Role role;					//角色
	private Integer enabled;			//是否可用
	
	@ManyToOne
	@JoinColumn(name = "user_id_", nullable = true)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@ManyToOne
	@JoinColumn(name = "role_id_", nullable = true)
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	@Column(name = "enabled_", nullable = false)
	public Integer getEnabled() {
		return enabled;
	}
	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}
}
