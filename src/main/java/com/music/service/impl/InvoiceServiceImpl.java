package com.music.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.music.entities.Cashback;
import com.music.entities.CatalogDisk;
import com.music.entities.Invoice;
import com.music.entities.ItemInvoice;
import com.music.repositories.CashbackRepository;
import com.music.repositories.CatalogDiskRepository;
import com.music.repositories.InvoiceRepository;
import com.music.service.InvoiceService;

@Service
public class InvoiceServiceImpl implements InvoiceService{
	
	private static final Logger log = LoggerFactory.getLogger(InvoiceServiceImpl.class);
	
	@Autowired
	private InvoiceRepository repository;
	
	@Autowired
	private CatalogDiskRepository diskRepository;
	
	@Autowired
	private CashbackRepository cashbackRepository;

	@Override
	public Page<Invoice> getInvoiceBetweenDates(String startDate, String endDate, Pageable pageable) {
		log.info("Buscando invoice pela data {}{}", startDate, endDate);
		return repository.getInvoiceBetweenDates(startDate, endDate, pageable);
	}

	@Override
	public Invoice save(Invoice invoice) {
		log.info("Salvando o pedido {}", invoice);
		
		try {
			//Calculando o cashback
			SimpleDateFormat typeDate = new SimpleDateFormat("dd/MM/yyyy");
			Calendar cal = Calendar.getInstance();
			Date data;		
			data = typeDate.parse(invoice.getDate());
			cal.setTime(data); 
			int day = cal.get(Calendar.DAY_OF_WEEK);
			
		    List<ItemInvoice> itens = invoice.getItens();
		    
			for (int i = 0; i < itens.size(); i++) {
				
				Optional<CatalogDisk> disk =  Optional.empty();
				disk = this.diskRepository.findById(itens.get(i).getDisk());
				
				Cashback cashback =  new Cashback();
				cashback = cashbackRepository.findByGenre(disk.get().getGenre().toLowerCase());
				
				int perc = 0;
				if(day == 1)
					perc = cashback.getPercSunday();
				if(day == 2)
					perc = cashback.getPercMonday();
				if(day == 3)
					perc = cashback.getPercTuesday();
				if(day == 4)
					perc = cashback.getPercWednesday();
				if(day == 5)
					perc = cashback.getPercThursday();
				if(day == 6)
					perc = cashback.getPercFriday();
				if(day == 7)
					perc = cashback.getPercSaturday();
				
				Double value = itens.get(i).getValue() * itens.get(i).getQuant();
				value = (value * perc) / 100;
				
				itens.get(i).setCashback(value);
			
			}
			
			invoice.setItens(itens);			
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return repository.save(invoice);
	}

	@Override
	public Optional<Invoice> searchById(Long id) {
		log.info("Buscando invoice pelo ID {}", id);
		return this.repository.findById(id);
	}

}
