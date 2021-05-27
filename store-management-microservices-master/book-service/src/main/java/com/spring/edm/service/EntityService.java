package com.spring.edm.service;

import java.util.List;
import java.util.Optional;

import com.spring.edm.entity.StoreEntity;

public interface EntityService {
	
	List<StoreEntity> findAllEntities();

	Optional<StoreEntity> findOneWithViewsById(int entityId);

	Optional<StoreEntity> findOneWithViewsByName(String entityName);

}
