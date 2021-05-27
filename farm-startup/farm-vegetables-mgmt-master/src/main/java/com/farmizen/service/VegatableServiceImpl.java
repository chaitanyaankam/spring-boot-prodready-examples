package com.farmizen.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.farmizen.domain.entity.Vegetable;
import com.farmizen.domain.entity.VegetableCategory;
import com.farmizen.domain.model.CategoryAssociation;
import com.farmizen.repository.CategoryRepository;
import com.farmizen.repository.VegetableRepository;

@Service
public class VegatableServiceImpl implements VegetableService {
	
	@Autowired
	private VegetableRepository vegetableRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW,isolation=Isolation.SERIALIZABLE)
	public void saveCategoryForVegatable(CategoryAssociation category) throws Exception {
		Optional<Vegetable> vegetable = Optional.ofNullable(vegetableRepository.findOne(category.getVegetableId()));
		Optional<VegetableCategory> vegetableCategory = Optional.ofNullable(categoryRepository.findOne(category.getCategoryId()));
		if(vegetable.isPresent() && vegetableCategory.isPresent()) {
			vegetableCategory.get().getVegetables().add(vegetable.get());
		}
	}

}
