package com.iokays.basefile.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.iokays.utils.domain.IdEntity;

/**
 * 基本文件信息实体类
 * @author pengyuanbing@gmail.com
 *
 */

@Entity
@Table(name = "tb_pub_base_file")
public class BaseFile extends IdEntity implements Serializable {

	private static final long serialVersionUID = 8301217335097456406L;
	
	private String name;			//文件名，为32位UUID
	private String md5;				//文件MD5码
	
	@Column(name = "name_", unique = true, nullable = false, length = 32)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "md5_", unique = true, nullable = false, length = 128)
	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}
}
