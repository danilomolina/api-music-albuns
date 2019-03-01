package com.music.service;


/**
 * Classe de Serviço para autenticação no Spotify
 * 
 * @author danilo.marques
 *
 */
public interface AuthorizationAPISpotify {
	
	/**
	 * Retorna o link para autenticação no spotify
	 * @return
	 */
	String getLinkAutorization();

}
