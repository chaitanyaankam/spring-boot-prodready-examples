package com.spring.edm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.edm.entity.StoreEntity;
import com.spring.edm.repository.EntityRepository;

@Service
public class EntityServiceImpl implements EntityService {

	@Autowired
	private EntityRepository entityRepository;
	
	@Override
	public List<StoreEntity> findAllEntities() {
		return entityRepository.findAll();
	}

	@Override
	@Transactional
	public Optional<StoreEntity> findOneWithViewsById(int entityId) {
		Optional<StoreEntity> optionalEntity = Optional.of(entityRepository.findOneWithEntityViewByEntityId(entityId));
		optionalEntity.get().getEntityView().forEach(e->System.out.println(e));
		return optionalEntity;
	}
	
	@Override
	@Transactional
	public Optional<StoreEntity> findOneWithViewsByName(String entityName) {
		Optional<StoreEntity> optionalEntity = Optional.of(entityRepository.findOneWithEntityViewByName(entityName));
		optionalEntity.get().getEntityView().forEach(e->System.out.println(e));
		return optionalEntity;
	}
	
}
