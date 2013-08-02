package com.iokays.user.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.iokays.usertorole.domain.UserToRole;

/**
 * 用户信息实体类
 * 
 * @author pengyuanbing@gmail.com
 * 
 */
@Entity
@Table(name="t_pub_user")
public class User implements Serializable {
	private static final long serialVersionUID = 2259087012492570233L;
	
	private String id;			//用户主键
	private String account;		//登陆账号
	private String name;		//用户名
	private String password;	//用户密码
	private Integer enabled;	//是否可用				0:禁用	1:正常
	private Integer isSys;		//是否是超级用户			0:非		1:是
	private String description;	//用户描述
	
	private Set<UserToRole> userToRoles;	//用户角色
	
	/**
	 * 默认构造函数
	 */
	public User() {	
		
	}

	@Id
	@GenericGenerator(name="idGenerator", strategy="uuid")		//主键生成策略为UUID
	@GeneratedValue(generator="idGenerator")
	@Column(name = "id_", unique = true, nullable = false, length = 32)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	
	@Column(name = "enabled_", nullable = false)
	public Integer getEnabled() {
		return enabled;
	}
	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	@Column(name = "is_sys_", nullable = false)
	public Integer getIsSys() {
		return isSys;
	}
	public void setIsSys(Integer isSys) {
		this.isSys = isSys;
	}

	@Column(name = "description_", length = 200, nullable = true)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@OneToMany(mappedBy = "role", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	public Set<UserToRole> getUserToRoles() {
		return userToRoles;
	}
	
	public void setUserToRoles(Set<UserToRole> userToRoles) {
		this.userToRoles = userToRoles;
	}

}
