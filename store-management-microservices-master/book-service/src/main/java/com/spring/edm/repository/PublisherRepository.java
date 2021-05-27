package com.spring.edm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.edm.entity.Publisher;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Integer> {
		
}
