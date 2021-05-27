package com.spring.edm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.edm.entity.Publisher;
import com.spring.edm.repository.PublisherRepository;

@Service
public class PublisherServiceImpl implements PublisherService {

	@Autowired
	private PublisherRepository publisherRepository;
	
	@Override
	public List<Publisher> findAllPublishers() {
		return publisherRepository.findAll();
	}

	
}
