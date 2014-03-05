package com.iokays.article.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.iokays.article.domain.Article;
import com.iokays.article.repository.plus.ArticleRepositoryPlus;
import com.iokays.column.domain.Column;

public interface ArticleRepository extends JpaRepository<Article, Serializable>, ArticleRepositoryPlus {

    public abstract List<Article> findByColumn(Column column);

    @Query("select t1 from Article t1 where t1.column.id = :columnId")
    public abstract List<Article> findByColumn(@Param("columnId") String columnId);

    @Query("select t1.id, t1.title, t1.column.name, t1.createDate from Article t1")
    public abstract Page<Object[]> findTitleAndColumnName(Pageable pageable);

    @Query("select t1.id, t1.title, t1.column.name, t1.createDate from Article t1 where t1.column.id = :columnId")
    public abstract Page<Object[]> findTitleAndColumnNameByColumnId(@Param("columnId") String columnId, Pageable pageable);

    @Query("select t1.id, t1.title, t1.column.name, t1.createDate from Article t1 where t1.column.id in :columnIds")
    public abstract Page<Object[]> findTitleAndColumnNameByColumnIdIn(@Param("columnIds") String[] columnIds, Pageable pageable);

    @Query("select t1.id, t1.title, t1.createDate from Article t1 where t1.column.parent.id = :columnParentId")
    public abstract Page<Object[]> findTitleAndCreateDateByColumnParentId(@Param("columnParentId") String columnParentId, Pageable pageable);

}
