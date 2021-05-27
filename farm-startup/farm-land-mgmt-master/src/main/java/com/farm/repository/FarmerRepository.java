package com.farm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import com.farm.domain.entity.Farmer;

@Transactional
@RepositoryRestResource(path="farmer", itemResourceRel="farmer")
public interface FarmerRepository extends JpaRepository<Farmer, String>, QueryDslPredicateExecutor<Farmer>{

}
