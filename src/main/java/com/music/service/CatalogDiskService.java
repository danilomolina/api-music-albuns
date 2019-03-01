package com.music.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.music.entities.CatalogDisk;

public interface CatalogDiskService {
	
	/**
	 * Cadastra um novo catalago de discos.
	 * 
	 * @param catalogDisk
	 * @return CatalogDisk
	 */
	CatalogDisk save(CatalogDisk catalogDisk);
	
	/**
	 * Retorna um catalogo pelo genero.
	 * 
	 * @param genre
	 * @return Optional<CatalogDisk>
	 */
	Page<CatalogDisk> searchByGenre(String genre, Pageable pageable);
	
	/**
	 * Retorna um catalogo pelo nome.
	 * 
	 * @param name
	 * @return Optional<CatalogDisk>
	 */
	Optional<CatalogDisk> searchByName(String name);
	
	/**
	 * Retorna um catalogo pelo id.
	 * 
	 * @param name
	 * @return Optional<CatalogDisk>
	 */
	Optional<CatalogDisk> searchById(Long id);

}
