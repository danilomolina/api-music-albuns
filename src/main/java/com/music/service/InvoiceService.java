package com.music.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.music.entities.Invoice;

public interface InvoiceService {

    /**
     * Retorna uma lista de pedidos , conforme o periodo passado
     * @param startDate
     * @param endDate
     * @return
     */
	Page<Invoice> getInvoiceBetweenDates(String startDate, String endDate, Pageable pageable);
	
	/**
	 * Cadastra um novo pedido.
	 * 
	 * @param invoice
	 * @return Invoice
	 */
	Invoice save(Invoice invoice);
	
	/**
	 * Retorna um catalogo pelo id.
	 * 
	 * @param name
	 * @return Optional<CatalogDisk>
	 */
	Optional<Invoice> searchById(Long id);
}

