package com.spring.edm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.spring.edm.entity.EntityView;

@RepositoryRestResource(collectionResourceRel = "entityViews", path = "entityViews")
public interface EntityViewRepository extends JpaRepository<EntityView,Integer> {
	
}
