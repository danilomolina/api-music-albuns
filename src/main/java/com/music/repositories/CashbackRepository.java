package com.music.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.music.entities.Cashback;

public interface CashbackRepository extends JpaRepository<Cashback, Long>{

	@Transactional(readOnly = true)
	Cashback findByGenre(String genre);

}
