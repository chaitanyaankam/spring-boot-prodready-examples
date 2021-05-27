package com.spring.edm.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.spring.edm.entity.StoreEntity;

@CrossOrigin(origins="http://localhost:4200", maxAge = 4800)
@RepositoryRestResource(collectionResourceRel = "entities", path = "entities")
public interface EntityRepository extends JpaRepository<StoreEntity, Integer> {
	
	@EntityGraph(attributePaths = { "entityView" })
	StoreEntity findOneWithEntityViewByEntityId(@Param("entityId") int id);
	
	@EntityGraph(attributePaths = { "entityView" })
	StoreEntity findOneWithEntityViewByName(@Param("name") String name);

}
