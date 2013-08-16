package com.iokays.user.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.iokays.usertorole.domain.UserToRole;
import com.iokays.utils.domain.IdEntity;
import com.iokays.utils.domain.Level;
import com.iokays.utils.domain.Status;

/**
 * 用户信息实体类
 * 
 * @author pengyuanbing@gmail.com
 * 
 */
@Entity
@Table(name="t_pub_user")
public class User extends IdEntity implements Serializable {
	private static final long serialVersionUID = 2259087012492570233L;
	
	private String account;		//登陆账号
	private String name;		//用户名
	private String password;	//用户密码
	private String description;	//用户描述
	private Status status;		//是否可用
	private Level level;		//资源等级
	
	private Set<UserToRole> userToRoles;	//用户角色
	
	/**
	 * 默认构造函数
	 */
	public User() {	
		
	}
	
	@Column(name = "account_", unique = true, length = 32, nullable = false)
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	
	@Column(name = "name_", length = 32, nullable = false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "password_", length = 32, nullable = false)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name ="status_", nullable = true)
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "level_", nullable = false)
	public Level getLevel() {
		return level;
	}
	public void setLevel(Level level) {
		this.level = level;
	}

	@Column(name = "description_", length = 200, nullable = true)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	public Set<UserToRole> getUserToRoles() {
		return userToRoles;
	}
	
	public void setUserToRoles(Set<UserToRole> userToRoles) {
		this.userToRoles = userToRoles;
	}

}
