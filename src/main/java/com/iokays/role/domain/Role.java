package com.iokays.role.domain;

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

import com.iokays.roletoauthority.domain.RoleToAuthority;
import com.iokays.usertorole.domain.UserToRole;
import com.iokays.utils.domain.IdEntity;
import com.iokays.utils.domain.Level;
import com.iokays.utils.domain.Status;

/**
 * 角色信息实体类
 * 
 * @author pengyuanbing@gmail.com
 *
 */

@Entity
@Table(name = "t_pub_role")
public class Role extends IdEntity implements Serializable{

	private static final long serialVersionUID = 5985194730311465091L;
	
	private String name;										//角色名称
	private String description;									//角色描述

	private Status states;										//是否可用
	private Level level;										//角色等级
	
	private Set<UserToRole> userToRoles;						//用户角色
	private Set<RoleToAuthority> roleToAuthorities;				//角色权限
	
	@Column(name = "name_", unique = true, nullable = false, length = 50)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "description_", length = 200)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	@Enumerated(EnumType.ORDINAL)
	@Column(name ="status_", nullable = true)
	public Status getStates() {
		return states;
	}
	public void setStates(Status states) {
		this.states = states;
	}
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "level_", nullable = false)
	public Level getLevel() {
		return level;
	}
	public void setLevel(Level level) {
		this.level = level;
	}
	
	@OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
	public Set<UserToRole> getUserToRoles() {
		return userToRoles;
	}
	public void setUserToRoles(Set<UserToRole> userToRoles) {
		this.userToRoles = userToRoles;
	}
	
	@OneToMany(mappedBy = "role", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	public Set<RoleToAuthority> getRoleToAuthorities() {
		return roleToAuthorities;
	}
	public void setRoleToAuthorities(Set<RoleToAuthority> roleToAuthorities) {
		this.roleToAuthorities = roleToAuthorities;
	}
}
