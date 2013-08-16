package com.iokays.roletoauthority.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.iokays.authority.domain.Authority;
import com.iokays.role.domain.Role;
import com.iokays.utils.domain.IdEntity;
import com.iokays.utils.domain.Status;

/**
 * 角色权限关联实体类
 * 
 * @author pengyuanbing@gmail.com
 *
 */
@Entity
@Table(name = "t_pub_role_authority")
public class RoleToAuthority extends IdEntity implements Serializable{

	private static final long serialVersionUID = -1931908140621449515L;
	
	private Role role;					//角色
	private Authority authority;		//权限
	private Status status;				//是否可用
	
	
	@ManyToOne
	@JoinColumn(name = "role_id_", nullable = false)
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	@ManyToOne
	@JoinColumn(name = "authority_id_", nullable = false)
	public Authority getAuthority() {
		return authority;
	}
	public void setAuthority(Authority authority) {
		this.authority = authority;
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
