package com.iokays.activitiform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iokays.activitiform.domain.ActivitiForm;

public interface ActivitiFormRepository extends JpaRepository<ActivitiForm, String>  {
	
}
