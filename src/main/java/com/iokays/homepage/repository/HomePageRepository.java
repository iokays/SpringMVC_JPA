package com.iokays.homepage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iokays.homepage.domain.HomePage;
import com.iokays.homepage.repository.plus.HomePageRepositoryPlus;

public interface HomePageRepository extends JpaRepository<HomePage, String>, HomePageRepositoryPlus {

}
