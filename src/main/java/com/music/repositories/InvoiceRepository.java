package com.music.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.music.entities.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Long>{
	
	@Query(value = "from Invoice t where date BETWEEN :startDate AND :endDate order by date desc")
	public Page<Invoice> getInvoiceBetweenDates(@Param("startDate")String startDate,@Param("endDate")String endDate, Pageable pageable); 
	
}
