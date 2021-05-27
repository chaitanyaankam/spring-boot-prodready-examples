package com.farmizen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.farmizen.domain.entity.VegetableCategory;

@Repository
@Transactional
@RepositoryRestResource
public interface CategoryRepository extends JpaRepository<VegetableCategory, Integer> {

}
