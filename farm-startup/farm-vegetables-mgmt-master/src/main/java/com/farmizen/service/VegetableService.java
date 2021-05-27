package com.farmizen.service;

import com.farmizen.domain.model.CategoryAssociation;

public interface VegetableService {

	void saveCategoryForVegatable(CategoryAssociation category) throws Exception;
}
