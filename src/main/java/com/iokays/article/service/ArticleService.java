package com.iokays.article.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.iokays.article.domain.Article;
import com.iokays.column.domain.Column;

public interface ArticleService {
	public abstract Page<Article> findAllByColumn(String columnId, Pageable pageable);

	public abstract Page<Article> findAllByColumnIn(String[] columnIds, Pageable pageable);

    public abstract List<Article> findAll();

    public abstract List<Article> findAll(Sort sort);

    public abstract Page<Article> findAll(Pageable pageable);

    public abstract Page<Article> pageable(Pageable pageable);

    public abstract Article findOne(String id);

    public abstract Article save(Article article);

    public abstract void delete(String id);

    public abstract Article update(String id, Map<String, Object> map);

    public abstract List<Article> findAllByColumn(Column column);

    public abstract List<Article> findAllByColumn(String columnId);

    public Page<Article> findAllByColumnParent(String columnParentId, Pageable pageable);

}