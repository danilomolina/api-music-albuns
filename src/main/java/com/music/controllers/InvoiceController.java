package com.music.controllers;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.music.Response;
import com.music.entities.Invoice;
import com.music.service.InvoiceService;

@RestController
@RequestMapping("/api/music/v1")
@CrossOrigin(origins = "*")
public class InvoiceController {

	@Autowired
	private InvoiceService invoiceService;
	
	
	@Value("${paginacao.qtd_por_pagina}")
	private int qtdPorPagina;
	
	private static final Logger log = LoggerFactory.getLogger(InvoiceController.class);
	
	/**
	 * Cadastra uma nova venda
	 * @param invoice
	 * @param result
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	@PostMapping
	public ResponseEntity<Response<Invoice>> save(@Valid @RequestBody Invoice invoice,
			BindingResult result) throws NoSuchAlgorithmException {
		log.info("Cadastrando um pedido: {}", invoice.toString());
		Response<Invoice> response = new Response<Invoice>();
	
		this.invoiceService.save(invoice);

		response.setData(invoice);
		return ResponseEntity.ok(response);
	}
	
	/**
	 * Retorna vendas a partir de um periodo de datas
	 * @param startDate
	 * @param endDate
	 * @param pag
	 * @param ord
	 * @param dir
	 * @return
	 */
	@GetMapping(value = "/invoice/{startDate}/{endDate}")
	public ResponseEntity<Response<Page<Invoice>>> getInvoiceBetweenDates(
			@PathVariable("startDate") @DateTimeFormat(iso=ISO.DATE) String startDate,
			@PathVariable("endDate") @DateTimeFormat(iso=ISO.DATE) String endDate,
			@RequestParam(value = "pag", defaultValue = "0") int pag,
			@RequestParam(value = "ord", defaultValue = "date") String ord,
			@RequestParam(value = "dir", defaultValue = "DESC") String dir) {
		log.info("Buscando vendas pela data inicial {} e data final {}", startDate, endDate , pag);
		Response<Page<Invoice>> response = new Response<Page<Invoice>>();

		PageRequest pageRequest =  PageRequest.of(pag, this.qtdPorPagina, Direction.valueOf(dir), ord);
		
		String newDateStart = startDate.replace(".", "/");
		String newDateEnd = endDate.replace(".", "/");
		
		Page<Invoice> invoices = this.invoiceService.getInvoiceBetweenDates(newDateStart, newDateEnd, pageRequest);

		response.setData(invoices);
		return ResponseEntity.ok(response);
	}
	
	/**
	 * Consulta venda pelo ID
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/invoice/{id}")
	public ResponseEntity<Response<Invoice>> searchById(@PathVariable("id") Long id) {
		log.info("Buscando venda por ID: {}", id);
		Response<Invoice> response = new Response<Invoice>();
		Optional<Invoice> invoice = this.invoiceService.searchById(id);		

		if (!invoice.isPresent()) {
			log.info("Invoice não encontrada para o ID: {}", id);
			response.getErrors().add("Invoice não encontrada para o ID " + id);
			return ResponseEntity.badRequest().body(response);
		}else
			response.setData(invoice.get());
		
		return ResponseEntity.ok(response);
	}
}
