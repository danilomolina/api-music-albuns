package com.music.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.music.entities.CatalogDisk;
import com.music.repositories.CatalogDiskRepository;
import com.music.service.CatalogDiskService;


@Service
public class CatalogDiskServiceImpl implements CatalogDiskService{

	
	private static final Logger log = LoggerFactory.getLogger(CatalogDiskServiceImpl.class);
	
	@Autowired
	private CatalogDiskRepository repository;

	@Override
	public CatalogDisk save(CatalogDisk catalogDisk) {
		log.info("Salvando catalogo: {}", catalogDisk);
		return this.repository.save(catalogDisk);
	}

	@Override
	public Page<CatalogDisk> searchByGenre(String genre, Pageable pageable) {
		log.info("Buscando disco pelo genero {}", genre);
		return repository.findByGenreOrderByGenreAsc(genre, pageable);
	}

	@Override
	public Optional<CatalogDisk> searchByName(String name) {
		log.info("Buscando disco pelo nome {}", name);
		return Optional.ofNullable(repository.findByNameOrderByNameAsc(name));
	}

	@Override
	public Optional<CatalogDisk> searchById(Long id) {
		log.info("Buscando disco pelo ID {}", id);
		return this.repository.findById(id);
	}
	

}
