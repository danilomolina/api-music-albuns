package com.music.service;

import com.music.entities.Cashback;

public interface CashBackService {
	
    /**
     * Retorna o cashback conforme o genero
     * @param genre
     * @return
     */
	Cashback getChascbackGenre(String genre); 

}
