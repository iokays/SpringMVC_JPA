package com.iokays.article.domain;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.iokays.column.domain.Column;
import com.iokays.utils.domain.IdEntity;

@Entity
@Table(name = "t_pub_article")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Article extends IdEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private String title; // 标题

	private byte[] content; // 内容

	private Column column; // 所属标题

	@javax.persistence.Column(name = "title_", length = 40, nullable = false)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Lob
	@Basic(fetch = FetchType.LAZY)
	@javax.persistence.Column(name = "content_", nullable = false)
	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "column_id_", nullable = false)
	public Column getColumn() {
		return column;
	}

	public void setColumn(Column column) {
		this.column = column;
	}

}
