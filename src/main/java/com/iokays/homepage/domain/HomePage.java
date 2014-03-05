package com.iokays.homepage.domain;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.iokays.utils.domain.IdEntity;

@Entity
@Table(name = "t_pub_home_page")
public class HomePage extends IdEntity implements Serializable {

    private static final long serialVersionUID = -4784844584245964547L;

    private String name;            //名称
    private String image;            //图片
    private String url;                //地址
    private Integer sort;            //排序

    @Column(name = "name_", length = 50, nullable = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(columnDefinition = "LONGTEXT", name = "image_", nullable = false)
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Column(name = "url_", length = 100, nullable = true)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Column(name = "sort_", nullable = false)
    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }


}
