package com.iokays.resource.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.iokays.authoritytorepository.domain.AuthorityToResource;
import com.iokays.utils.domain.IdEntity;

/**
 * 资源信息实体类
 * 
 * @author pengyuanbing@gmail.com
 *
 */
@Entity
@Table(name = "t_pub_resource")
public class Resource extends IdEntity implements Serializable{

	private static final long serialVersionUID = 7814877404789755510L;
	
	private String name;										//资源名称
	private String type;										//资源类型 method, url
	private String value;										//资源链接
	private Integer priority;									//资源优先权
	private String description;									//资源描述
	
	private Integer enabled;									//是否可用				0:禁用	1:正常
	private Integer isSys;										//是否是超级资源			0:非		1:是
	
	private Set<AuthorityToResource> authorityToResources;	//权限资源
	
	@Column(name = "name_", unique = true, nullable = false, length = 50)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "type_", nullable = false, length = 10)
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@Column(name = "value_", nullable = false, length = 50)
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	@Column(name = "priority_")
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
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
	
	@Column(name = "is_sys_")
	public Integer getIsSys() {
		return isSys;
	}
	public void setIsSys(Integer isSys) {
		this.isSys = isSys;
	}
	
	@OneToMany(mappedBy = "resource", fetch = FetchType.LAZY)
	public Set<AuthorityToResource> getAuthorityToResources() {
		return authorityToResources;
	}
	public void setAuthorityToResources(
			Set<AuthorityToResource> authorityToResources) {
		this.authorityToResources = authorityToResources;
	}
	
}
