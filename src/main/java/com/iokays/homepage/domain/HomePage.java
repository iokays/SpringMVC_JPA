package com.iokays.homepage.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.iokays.utils.domain.IdEntity;

@Entity
@Table(name = "t_pub_home_page")
public class HomePage extends IdEntity implements Serializable {

	private static final long serialVersionUID = -4784844584245964547L;

	private String name; // 名称

	private String target; // 指向

	private Integer sort; // 排序

	@Column(name = "name_", length = 50, nullable = true)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "target_", length = 50, nullable = false)
	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	@Column(name = "sort_", nullable = false)
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

}
