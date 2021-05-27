package com.spring.edm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.edm.entity.Genre;
import com.spring.edm.repository.GenreRepository;

@Service
public class GenreServiceImpl implements GenreService {

	@Autowired
	private GenreRepository genreRepository;
	
	@Override
	public List<Genre> findAllGenre() {
		return genreRepository.findAll();
	}

	
}
