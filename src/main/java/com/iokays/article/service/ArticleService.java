package com.iokays.article.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;

import com.iokays.article.domain.Article;
import com.iokays.column.domain.Column;

public interface ArticleService {
    public abstract Page<Object[]> findTitleAndColumnNameByColumnId(String columnId, Pageable pageable);

    public abstract Page<Object[]> findTitleAndColumnNameByColumnIdIn(String[] columnIds, Pageable pageable);

    public abstract List<Article> findAll();

    public abstract List<Article> findAll(Sort sort);

    public abstract Page<Article> findAll(Pageable pageable);

    public abstract Page<Object[]> findTitleAndColumnName(Pageable pageable);

    public abstract Article findOne(Serializable id);

    public abstract Article save(Article article);

    public abstract Integer delete(Serializable id);

    public abstract Integer update(Serializable id, Map<String, Object> map) throws Exception;

    public abstract List<Article> findByColumn(Column column);

    public abstract List<Article> findByColumn(@Param("columnId") String columnId);

    public abstract Page<Object[]> findTitleAndCreateDateByColumnParentId(String columnParentId, Pageable pageable);

}