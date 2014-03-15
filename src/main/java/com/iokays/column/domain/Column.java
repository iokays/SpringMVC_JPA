package com.iokays.column.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.iokays.article.domain.Article;
import com.iokays.utils.domain.IdEntity;

@Entity
@Table(name = "t_pub_column")
public class Column extends IdEntity implements Serializable {

    private static final long serialVersionUID = 3524851152337233294L;

    private String name;            //栏目名称

    public enum Grade {
        one, two
    }

    private Grade grade;            //栏目级别
    private Column parent;            //父级栏目
    private List<Column> children;    //子级栏目
    private List<Article> articles;    //包含文章
    private Integer sort;            //排序
    private String description;        //描述
    private String template;        //模版
    private String imageUrl;            //图片

    public Column() {

    }

    @javax.persistence.Column(name = "name_", length = 20, unique = true, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Enumerated(EnumType.ORDINAL)
    @javax.persistence.Column(name = "grade_", nullable = false)
    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id_", nullable = true)
    public Column getParent() {
        return parent;
    }

    public void setParent(Column parent) {
        this.parent = parent;
    }

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    public List<Column> getChildren() {
        return children;
    }

    public void setChildren(List<Column> children) {
        this.children = children;
    }

    @javax.persistence.Column(name = "sort_", nullable = false)
    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @javax.persistence.Column(name = "description_", length = 200)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @OneToMany(mappedBy = "column", fetch = FetchType.LAZY)
    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    @javax.persistence.Column(name = "template_", length = 20)
    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @javax.persistence.Column(columnDefinition = "LONGTEXT", name = "image_url_", nullable = false)
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
