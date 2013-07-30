package com.iokays.resource.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.iokays.authoritietorepository.domain.AuthoritieToResource;

/**
 * 资源信息实体类
 * 
 * @author pengyuanbing@gmail.com
 *
 */
@Entity
@Table(name = "t_pub_resource")
public class Resource implements Serializable{

	private static final long serialVersionUID = 7814877404789755510L;
	
	private String id;											//资源唯一标识符
	private String name;										//资源名称
	private String type;										//资源类型 method, url
	private String resource;									//资源链接
	private Integer priority;									//资源优先权
	private String description;									//资源描述
	
	private Integer enabled;									//是否可用				0:禁用	1:正常
	private Integer isSys;										//是否是超级资源			0:非		1:是
	
	private Set<AuthoritieToResource> authoritieToResources;	//权限资源
	
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
	
	@Column(name = "type", nullable = false, length = 10)
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@Column(name = "resource", nullable = false, length = 50)
	public String getResource() {
		return resource;
	}
	public void setResource(String resource) {
		this.resource = resource;
	}
	
	@Column(name = "priority")
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
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
	
	@Column(name = "isSys")
	public Integer getIsSys() {
		return isSys;
	}
	public void setIsSys(Integer isSys) {
		this.isSys = isSys;
	}
	
	@OneToMany(mappedBy = "resource", fetch = FetchType.LAZY)
	public Set<AuthoritieToResource> getAuthoritieToResources() {
		return authoritieToResources;
	}
	public void setAuthoritieToResources(
			Set<AuthoritieToResource> authoritieToResources) {
		this.authoritieToResources = authoritieToResources;
	}
	
}
