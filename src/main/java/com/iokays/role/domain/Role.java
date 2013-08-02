package com.iokays.role.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.iokays.roletoauthority.domain.RoleToAuthority;
import com.iokays.usertorole.domain.UserToRole;
import com.iokays.utils.domain.IdEntity;

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
	
	private String name;									//角色名称
	private Integer isSys;									//是否是超级角色			0:非		1:是
	private String description;								//角色描述
	private Integer enabled;								//是否可用				0:禁用	1:正常
	
	private Set<UserToRole> userToRoles;					//用户角色
	private Set<RoleToAuthority> roleToAuthorities;		//角色权限
	
	@Column(name = "name_", unique = true, nullable = false, length = 50)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "is_sys_")
	public Integer getIsSys() {
		return isSys;
	}
	public void setIsSys(Integer isSys) {
		this.isSys = isSys;
	}
	
	@Column(name = "description_", length = 200)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "enabled_", nullable = false)
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
	
	@OneToMany(mappedBy = "authority", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	public Set<RoleToAuthority> getRoleToAuthorities() {
		return roleToAuthorities;
	}
	public void setRoleToAuthorities(Set<RoleToAuthority> roleToAuthorities) {
		this.roleToAuthorities = roleToAuthorities;
	}
	
	
	
	
	
}
