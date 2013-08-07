package com.iokays.authoritytorepository.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.iokays.authority.domain.Authority;
import com.iokays.resource.domain.Resource;
import com.iokays.utils.domain.IdEntity;
import com.iokays.utils.domain.Status;

/**
 * 权限资源关联实体类
 * 
 * @author pengyuanbing@gmail.com
 *
 */

@Entity
@Table(name = "t_pub_authority_resource")
public class AuthorityToResource extends IdEntity implements Serializable{

	private static final long serialVersionUID = 3842203721619078799L;
	
	private Authority authority;			//权限
	private Resource resource;				//资源
	private Status states;					//是否可用
	
	@ManyToOne
	@JoinColumn(name = "authority_id_", nullable = false)
	public Authority getAuthority() {
		return authority;
	}
	public void setAuthority(Authority authority) {
		this.authority = authority;
	}
	
	@ManyToOne
	@JoinColumn(name = "resource_id_", nullable = false)
	public Resource getResource() {
		return resource;
	}
	public void setResource(Resource resource) {
		this.resource = resource;
	}
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name ="status_", nullable = true)
	public Status getStates() {
		return states;
	}
	public void setStates(Status states) {
		this.states = states;
	}
}
