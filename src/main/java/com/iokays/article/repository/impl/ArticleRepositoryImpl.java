package com.iokays.article.repository.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.iokays.article.repository.plus.ArticleRepositoryPlus;

public class ArticleRepositoryImpl implements ArticleRepositoryPlus {

    @PersistenceContext
    private EntityManager entityManager;


}
