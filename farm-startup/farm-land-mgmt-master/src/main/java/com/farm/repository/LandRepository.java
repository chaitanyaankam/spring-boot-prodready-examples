package com.farm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.farm.domain.entity.Land;

@Repository
@RepositoryRestResource(path="lands", itemResourceRel="lands")
@Transactional
public interface LandRepository extends JpaRepository<Land, Integer>, QueryDslPredicateExecutor<Land> {
	
}
