package com.iokays.roletoauthoritie.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.iokays.authoritie.domain.Authoritie;
import com.iokays.role.domain.Role;

/**
 * 角色权限关联实体类
 * 
 * @author pengyuanbing@gmail.com
 *
 */
@Entity
@Table(name = "t_pub_role_authoritie")
public class RoleToAuthoritie implements Serializable{

	private static final long serialVersionUID = -1931908140621449515L;
	
	private String id;					//唯一标识符
	private Role role;					//角色
	private Authoritie authoritie;		//权限
	private Integer enabled;			//是否可用
	
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
	
	@ManyToOne
	@JoinColumn(name = "roleId", nullable = false)
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	@ManyToOne
	@JoinColumn(name = "authoritieId", nullable = false)
	public Authoritie getAuthoritie() {
		return authoritie;
	}
	public void setAuthoritie(Authoritie authoritie) {
		this.authoritie = authoritie;
	}
	
	@Column(name = "enabled", nullable = false)
	public Integer getEnabled() {
		return enabled;
	}
	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}
}
