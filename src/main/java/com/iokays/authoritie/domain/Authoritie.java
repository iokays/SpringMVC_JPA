package com.iokays.authoritie.domain;

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

import com.iokays.authoritietorepository.domain.AuthoritieToResource;
import com.iokays.roletoauthoritie.domain.RoleToAuthoritie;

/**
 *  权限信息实体类
 *  
 * @author pengyuanbing@gmail.com
 *
 */

@Entity
@Table(name = "t_pub_authoritie")
public class Authoritie implements Serializable {

	private static final long serialVersionUID = -4571074703857910904L;
	
	private String id;										//权限ID
	private String name;									//权限名称
	private Integer isSys;									//是否是超级权限			0:非		1:是
	private String description;								//权限描述
	private Integer enabled;								//是否可用
	
	private Set<RoleToAuthoritie> roleToAuthorities;		//角色权限
	private Set<AuthoritieToResource> authoritieToResources;//权限资源
	
	/**
	 * 默认构造函数
	 */
	public Authoritie() {
		
	}
	
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
	
	@Column(name = "isSys", nullable = false)
	public Integer getIsSys() {
		return isSys;
	}
	public void setIsSys(Integer isSys) {
		this.isSys = isSys;
	}
	
	@Column(name = "description", nullable = true, length = 200)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name ="enabled", nullable = true)
	public Integer getEnabled() {
		return enabled;
	}
	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}
	
	@OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
	public Set<RoleToAuthoritie> getRoleToAuthorities() {
		return roleToAuthorities;
	}
	public void setRoleToAuthorities(Set<RoleToAuthoritie> roleToAuthorities) {
		this.roleToAuthorities = roleToAuthorities;
	}
	
	@OneToMany(mappedBy = "authoritie", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	public Set<AuthoritieToResource> getAuthoritieToResources() {
		return authoritieToResources;
	}
	public void setAuthoritieToResources(Set<AuthoritieToResource> authoritieToResources) {
		this.authoritieToResources = authoritieToResources;
	}
}
