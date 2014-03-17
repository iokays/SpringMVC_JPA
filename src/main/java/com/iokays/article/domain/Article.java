package com.iokays.article.domain;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.iokays.column.domain.Column;
import com.iokays.utils.domain.IdEntity;

@Entity
@Table(name = "t_pub_article")
public class Article extends IdEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String title;                    	//标题
    private String content;                   	//内容

    private Column column;                    	//所属标题
    
    private String imageUrl;            		//图片

    @javax.persistence.Column(name = "title_", length = 40, nullable = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @javax.persistence.Column(columnDefinition = "LONGTEXT", name = "content_", nullable = false)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
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
    
    @javax.persistence.Column(name = "image_url_", length = 50, unique = true, nullable = false)
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
