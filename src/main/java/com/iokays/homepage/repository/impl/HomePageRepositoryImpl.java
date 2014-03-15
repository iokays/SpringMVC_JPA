package com.iokays.homepage.repository.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.iokays.homepage.repository.plus.HomePageRepositoryPlus;

public class HomePageRepositoryImpl implements HomePageRepositoryPlus {
    @PersistenceContext
    private EntityManager entityManager;

}
