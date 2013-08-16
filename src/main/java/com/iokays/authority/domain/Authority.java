package com.iokays.authority.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.iokays.authoritytorepository.domain.AuthorityToResource;
import com.iokays.roletoauthority.domain.RoleToAuthority;
import com.iokays.utils.domain.IdEntity;
import com.iokays.utils.domain.Level;
import com.iokays.utils.domain.Status;

/**
 *  权限信息实体类
 *  
 * @author pengyuanbing@gmail.com
 *
 */

@Entity
@Table(name = "t_pub_authority")
public class Authority extends IdEntity implements Serializable {

	private static final long serialVersionUID = -4571074703857910904L;
	
	private String name;									//权限名称
	private String description;								//权限描述
	
	private Status status;									//是否可用
	private Level level;									//权限等级
	
	private Set<RoleToAuthority> roleToAuthorities;			//角色权限
	private Set<AuthorityToResource> authorityToResources;	//权限资源
	
	/**
	 * 默认构造函数
	 */
	public Authority() {
		
	}
	
	@Column(name = "name_", unique = true, nullable = false, length = 50)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "description_", nullable = true, length = 200)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name ="status_", nullable = false)
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

	@OneToMany(mappedBy = "authority", fetch = FetchType.LAZY)
	public Set<RoleToAuthority> getRoleToAuthorities() {
		return roleToAuthorities;
	}

	public void setRoleToAuthorities(Set<RoleToAuthority> roleToAuthorities) {
		this.roleToAuthorities = roleToAuthorities;
	}

	@OneToMany(mappedBy = "authority", fetch = FetchType.LAZY)
	public Set<AuthorityToResource> getAuthorityToResources() {
		return authorityToResources;
	}

	public void setAuthorityToResources(
			Set<AuthorityToResource> authorityToResources) {
		this.authorityToResources = authorityToResources;
	}
}
