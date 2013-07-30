package com.iokays.role.domain;

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

import com.iokays.roletoauthoritie.domain.RoleToAuthoritie;
import com.iokays.usertorole.domain.UserToRole;

/**
 * 角色信息实体类
 * 
 * @author pengyuanbing@gmail.com
 *
 */

@Entity
@Table(name = "t_pub_role")
public class Role implements Serializable{

	private static final long serialVersionUID = 5985194730311465091L;
	
	private String id;										//角色ID
	private String name;									//角色名称
	private Integer isSys;									//是否是超级角色			0:非		1:是
	private String description;								//角色描述
	private Integer enabled;								//是否可用				0:禁用	1:正常
	
	private Set<UserToRole> userToRoles;					//用户角色
	private Set<RoleToAuthoritie> roleToAuthorities;		//角色权限
	
	@Id
	@GenericGenerator(name="idGenerator", strategy="uuid")		//主键生成策略为UUID
	@GeneratedValue(generator="idGenerator")
	@Column(name = "id", unique = true, nullable = false, length = 32)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Column(name = "name", unique = true, nullable = false, length = 50)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "isSys")
	public Integer getIsSys() {
		return isSys;
	}
	public void setIsSys(Integer isSys) {
		this.isSys = isSys;
	}
	
	@Column(name = "description", length = 200)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "enabled", nullable = false)
	public Integer getEnabled() {
		return enabled;
	}
	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}
	
	@OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
	public Set<UserToRole> getUserToRoles() {
		return userToRoles;
	}
	public void setUserToRoles(Set<UserToRole> userToRoles) {
		this.userToRoles = userToRoles;
	}
	
	@OneToMany(mappedBy = "role", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	public Set<RoleToAuthoritie> getRoleToAuthorities() {
		return roleToAuthorities;
	}
	public void setRoleToAuthorities(Set<RoleToAuthoritie> roleToAuthorities) {
		this.roleToAuthorities = roleToAuthorities;
	}
	
	
	
}
