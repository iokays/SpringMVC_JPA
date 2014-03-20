package com.iokays.article.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ArticleServiceImpl.class);

    public Page<Article> findAll(Pageable pageable) {
        return articleRepository.findAll(pageable);
    }

    public List<Article> findAllByColumn(Column column) {
        return articleRepository.findAllByColumn(column);
    }

    public List<Article> findAllByColumn(@Param("columnId") String columnId) {
        return articleRepository.findAllByColumn(columnId);
    }

    /* (non-Javadoc)
     * @see com.iokays.article.service.ArticleService#findAll()
     */
    @Override
    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    @Override
    public Page<Article> findAllByColumn(String columnId, Pageable pageable) {
        return articleRepository.findAllByColumn(columnId, pageable);
    }

    @Override
    public Page<Article> findAllByColumnIn(String[] columnIds, Pageable pageable) {
        if (1 == columnIds.length) {
            return articleRepository.findAllByColumn(columnIds[0], pageable);
        } else {
            return articleRepository.findAllByColumnIn(columnIds, pageable);
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
    public Page<Article> pageable(Pageable pageable) {
        return articleRepository.findAll(pageable);
    }

    /* (non-Javadoc)
     * @see com.iokays.article.service.ArticleService#findOne(java.io.Serializable)
     */
    @Override
    public Article findOne(String id) {
    	Article article = articleRepository.findOne(id);
    	
       return article;
    }

    /* (non-Javadoc)
     * @see com.iokays.article.service.ArticleService#save(com.iokays.article.domain.Article)
     */
    @Override
    public Article save(Article article) {
        return articleRepository.save(article);
    }

    @Override
    public void delete(String id) {
        articleRepository.delete(id);
    }

    public Article update(String id, Map<String, Object> map) {
    	LOGGER.debug("map:{}", map);
    	Article article = articleRepository.findOne(id);
    	if (map.containsKey("id")) { article.setId(map.get("id").toString()); map.remove("id"); }
    	if (map.containsKey("title")) { article.setTitle(map.get("title").toString()); map.remove("title"); }
    	if (map.containsKey("content")) { article.setContent(map.get("content").toString().getBytes()); map.remove("content"); }
    	if (map.containsKey("column.id")) {
    		Column column = new Column(map.remove("column.id").toString());
    		if (!column.getId().equals(article.getColumn().getId())) {
    			article.setColumn(column);
    		}
    	}
    	
    	return article;
    }

    public Page<Article> findAllByColumnParent(String columnParentId, Pageable pageable) {
        return articleRepository.findAllByColumnParent(columnParentId, pageable);
    }

    @Resource
    private ArticleRepository articleRepository;

}
