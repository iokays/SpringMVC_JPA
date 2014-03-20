package com.iokays.article.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.iokays.article.domain.Article;
import com.iokays.article.repository.plus.ArticleRepositoryPlus;
import com.iokays.column.domain.Column;

public interface ArticleRepository extends JpaRepository<Article, String>, ArticleRepositoryPlus {

    public abstract List<Article> findAllByColumn(Column column);

    @Query("select t1 from Article t1 where t1.column.id = :columnId")
    public abstract List<Article> findAllByColumn(@Param("columnId") String columnId);
    
    @Query("select t1 from Article t1 where t1.column.id = :columnId")
    public abstract Page<Article> findAllByColumn(@Param("columnId") String columnId, Pageable pageable);

   
    @Query("select t1 from Article t1 where t1.column.id in :columnIds")
    public abstract Page<Article> findAllByColumnIn(@Param("columnIds") String[] columnIds, Pageable pageable);

    @Query("select t1 from Article t1 where t1.column.parent.id = :columnParentId")
    public abstract Page<Article> findAllByColumnParent(@Param("columnParentId") String columnParentId, Pageable pageable);

}
