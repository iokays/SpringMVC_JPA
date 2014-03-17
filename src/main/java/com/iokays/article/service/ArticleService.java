package com.iokays.article.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.iokays.article.domain.Article;
import com.iokays.column.domain.Column;

public interface ArticleService {
    public abstract Page<Object[]> findTitleAndColumnNameByColumnId(String columnId, Pageable pageable);

    public abstract Page<Object[]> findTitleAndColumnNameByColumnIdIn(String[] columnIds, Pageable pageable);

    public abstract List<Article> findAll();

    public abstract List<Article> findAll(Sort sort);

    public abstract Page<Article> findAll(Pageable pageable);

    public abstract Page<Object[]> findTitleAndColumnName(Pageable pageable);

    public abstract Article findOne(String id);

    public abstract Article save(Article article);

    public abstract void delete(String id);

    public abstract Article update(String id, Map<String, Object> map);

    public abstract List<Article> findByColumn(Column column);

    public abstract List<Article> findByColumn(String columnId);

    public abstract Page<Object[]> findTitleAndCreateDateByColumnParentId(String columnParentId, Pageable pageable);

}