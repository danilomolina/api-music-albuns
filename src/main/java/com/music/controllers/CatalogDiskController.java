package com.music.controllers;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.music.Response;
import com.music.entities.CatalogDisk;
import com.music.service.CatalogDiskService;

@RestController
@RequestMapping("/api/music/v1")
@CrossOrigin(origins = "*")
public class CatalogDiskController {
	
	private static final Logger log = LoggerFactory.getLogger(CatalogDiskController.class);
	
	@Autowired
	private CatalogDiskService diskService;
	
	@Value("${paginacao.qtd_por_pagina}")
	private int qtdPorPagina;

	/**
	 * Consulta o catálogo de discos de forma paginada, filtrando por gênero e ordenando de forma crescente 
	 * pelo nome do disco
	 * @param genre
	 * @param pag
	 * @param ord
	 * @param dir
	 * @return
	 */
	@GetMapping(value = "/catalog/genre/{genre}")
	public ResponseEntity<Response<Page<CatalogDisk>>> searchByGenre(
			@PathVariable("genre") String genre,
			@RequestParam(value = "pag", defaultValue = "0") int pag,
			@RequestParam(value = "ord", defaultValue = "name") String ord,
			@RequestParam(value = "dir", defaultValue = "DESC") String dir) {
		log.info("Buscando albuns por genero: {}, página: {}", genre, pag);
		Response<Page<CatalogDisk>> response = new Response<Page<CatalogDisk>>();

		PageRequest pageRequest =  PageRequest.of(pag, this.qtdPorPagina, Direction.valueOf(dir), ord);
		
		Page<CatalogDisk> disks = this.diskService.searchByGenre(genre, pageRequest);
		

		response.setData(disks);
		return ResponseEntity.ok(response);
	}
	
	/**
	 * Consulta catalago pelo ID
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/catalog/{id}")
	public ResponseEntity<Response<CatalogDisk>> searchById(@PathVariable("id") Long id) {
		log.info("Buscando catalogo por ID: {}", id);
		Response<CatalogDisk> response = new Response<CatalogDisk>();
		Optional<CatalogDisk> catalog = this.diskService.searchById(id);		

		if (!catalog.isPresent()) {
			log.info("Catalogo não encontrado para o ID: {}", id);
			response.getErrors().add("Catalago não encontrado para o id " + id);
			return ResponseEntity.badRequest().body(response);
		}else
			response.setData(catalog.get());
		
		return ResponseEntity.ok(response);
	}

}
