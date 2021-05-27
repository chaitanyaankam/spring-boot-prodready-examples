package com.farm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.transaction.annotation.Transactional;

import com.farm.domain.entity.Farm;

@Transactional
public interface FarmRepository extends JpaRepository<Farm, String>, QueryDslPredicateExecutor<Farm> {

}
