package com.iokays.authoritietorepository.domain;

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
import com.iokays.resource.domain.Resource;

/**
 * 权限资源关联实体类
 * 
 * @author pengyuanbing@gmail.com
 *
 */

@Entity
@Table(name = "t_pub_authoritie_resource")
public class AuthoritieToResource implements Serializable{

	private static final long serialVersionUID = 3842203721619078799L;
	
	private String id;						//唯一标识符
	private Authoritie authoritie;			//权限
	private Resource resource;				//资源
	private Integer enabled;				//是否可用
	
	@Id
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	@Column(name = "id", unique = true, nullable = false, length = 32)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@ManyToOne
	@JoinColumn(name = "authoritieId", nullable = false)
	public Authoritie getAuthoritie() {
		return authoritie;
	}
	public void setAuthoritie(Authoritie authoritie) {
		this.authoritie = authoritie;
	}
	
	@ManyToOne
	@JoinColumn(name = "resourceId", nullable = false)
	public Resource getResource() {
		return resource;
	}
	public void setResource(Resource resource) {
		this.resource = resource;
	}
	
	@Column(name = "enabled", nullable = false)
	public Integer getEnabled() {
		return enabled;
	}
	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}
}
