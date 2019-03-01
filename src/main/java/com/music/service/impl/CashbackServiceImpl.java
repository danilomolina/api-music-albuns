package com.music.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.music.entities.Cashback;
import com.music.repositories.CashbackRepository;
import com.music.service.CashBackService;

@Service
public class CashbackServiceImpl implements CashBackService{
	
	private static final Logger log = LoggerFactory.getLogger(CashbackServiceImpl.class);
	
	@Autowired
	CashbackRepository cashbackRepository;

	@Override
	public Cashback getChascbackGenre(String genre) {
		log.info("Buscando cashback pelo genero {}", genre);
		return cashbackRepository.findByGenre(genre);
	}
	


}
