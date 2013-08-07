package com.iokays.resource.domain;

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
import com.iokays.utils.domain.IdEntity;
import com.iokays.utils.domain.Level;
import com.iokays.utils.domain.Status;

/**
 * 资源信息实体类
 * 
 * @author pengyuanbing@gmail.com
 *
 */
@Entity
@Table(name = "t_pub_resource")
public class Resource extends IdEntity implements Serializable {

	private static final long serialVersionUID = 7814877404789755510L;
	
	private String name;										//资源名称
	private String type;										//资源类型 method, url
	private String value;										//资源链接
	private Integer priority;									//资源优先权
	private String description;									//资源描述
	
	private Status states;										//是否可用
	private Level level;										//资源等级
	private Set<AuthorityToResource> authorityToResources;		//权限资源
	
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
	
	@OneToMany(mappedBy = "resource", fetch = FetchType.LAZY)
	public Set<AuthorityToResource> getAuthorityToResources() {
		return authorityToResources;
	}
	public void setAuthorityToResources(
			Set<AuthorityToResource> authorityToResources) {
		this.authorityToResources = authorityToResources;
	}
	
}
