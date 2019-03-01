package com.music.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.music.entities.ItemInvoice;

public interface ItemInvoiceRepository extends JpaRepository<ItemInvoice, Long>{

}
