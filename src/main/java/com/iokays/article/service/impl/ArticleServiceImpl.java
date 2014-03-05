package com.iokays.article.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iokays.article.domain.Article;
import com.iokays.article.repository.ArticleRepository;
import com.iokays.article.service.ArticleService;
import com.iokays.column.domain.Column;

@Service("articleService")
@Transactional
public class ArticleServiceImpl implements ArticleService {

    public Page<Article> findAll(Pageable pageable) {
        return articleRepository.findAll(pageable);
    }

    public List<Article> findByColumn(Column column) {
        return articleRepository.findByColumn(column);
    }

    public List<Article> findByColumn(@Param("columnId") String columnId) {
        return articleRepository.findByColumn(columnId);
    }

    /* (non-Javadoc)
     * @see com.iokays.article.service.ArticleService#findAll()
     */
    @Override
    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    @Override
    public Page<Object[]> findTitleAndColumnNameByColumnId(String columnId, Pageable pageable) {
        return articleRepository.findTitleAndColumnNameByColumnId(columnId, pageable);
    }

    @Override
    public Page<Object[]> findTitleAndColumnNameByColumnIdIn(String[] columnIds, Pageable pageable) {
        if (1 == columnIds.length) {
            return articleRepository.findTitleAndColumnNameByColumnId(columnIds[0], pageable);
        } else {
            return articleRepository.findTitleAndColumnNameByColumnIdIn(columnIds, pageable);
        }

    }

    /* (non-Javadoc)
     * @see com.iokays.article.service.ArticleService#findAll(org.springframework.data.domain.Sort)
     */
    @Override
    public List<Article> findAll(Sort sort) {
        return articleRepository.findAll(sort);
    }

    /* (non-Javadoc)
     * @see com.iokays.article.service.ArticleService#findAll(org.springframework.data.domain.Pageable)
     */
    @Override
    public Page<Object[]> findTitleAndColumnName(Pageable pageable) {
        return articleRepository.findTitleAndColumnName(pageable);
    }

    /* (non-Javadoc)
     * @see com.iokays.article.service.ArticleService#findOne(java.io.Serializable)
     */
    @Override
    public Article findOne(Serializable id) {
        return articleRepository.findOne(id);
    }

    /* (non-Javadoc)
     * @see com.iokays.article.service.ArticleService#save(com.iokays.article.domain.Article)
     */
    @Override
    public Article save(Article article) {
        return articleRepository.save(article);
    }

    @Override
    public Integer delete(Serializable id) {
        return articleRepository.deleteById(id);
    }

    public Integer update(Serializable id, Map<String, Object> map) throws Exception {
        return articleRepository.update(id, map);
    }

    public Page<Object[]> findTitleAndCreateDateByColumnParentId(String columnParentId, Pageable pageable) {
        return articleRepository.findTitleAndCreateDateByColumnParentId(columnParentId, pageable);
    }

    @Resource
    private ArticleRepository articleRepository;

}
