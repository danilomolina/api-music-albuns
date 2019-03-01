package com.music.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;


import com.music.entities.CatalogDisk;



public interface CatalogDiskRepository extends JpaRepository<CatalogDisk, Long>{
	
	@Transactional(readOnly = true)
	Page<CatalogDisk> findByGenreOrderByGenreAsc(String genre, Pageable pageable);
	
	@Transactional(readOnly = true)
	CatalogDisk findByNameOrderByNameAsc(String name);

}
