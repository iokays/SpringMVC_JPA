package com.iokays.activitiform.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.iokays.utils.domain.IdEntity;

@Entity
@Table(name= "_activiti_form")
public class ActivitiForm extends IdEntity implements Serializable {

	private static final long serialVersionUID = -518398502221557788L;
	
	private String name;
	
	private String description;
	
	public ActivitiForm() {
		// TODO Auto-generated constructor stub
	}
	
	@Column(name = "name_", unique = true, nullable = true, length = 50)
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

}
