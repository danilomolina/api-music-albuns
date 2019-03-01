package com.music.service;

public interface AlbumsService {
	
	/**
	 * Busca albuns na API do spotify e salva no banco de dados
	 * 
	 */
	void setSeveralAlbums(String accessToken);	
	

}
