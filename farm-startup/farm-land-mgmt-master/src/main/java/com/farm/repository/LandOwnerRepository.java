package com.farm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.farm.domain.entity.LandOwner;

@Repository
@Transactional
@RepositoryRestResource(path="owners", itemResourceRel="owners")
public interface LandOwnerRepository extends JpaRepository<LandOwner, String>, QueryDslPredicateExecutor<LandOwner>{

}
