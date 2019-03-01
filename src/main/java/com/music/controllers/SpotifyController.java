package com.music.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.music.service.AlbumsService;
import com.music.service.AuthorizationAPISpotify;

@RestController
@RequestMapping("/api/music/v1")
@CrossOrigin(origins = "*")
public class SpotifyController {
	
	private static final Logger log = LoggerFactory.getLogger(SpotifyController.class);
	
	@Autowired
	private AuthorizationAPISpotify apiSportifyService;
	
	@Autowired
	private AlbumsService albumsService;
	
	/**
	 * Retorna do link de autenticação.
	 *
	 * @return ResponseEntity<String>
	 */
	@GetMapping(value = "/autorization")
	public ResponseEntity<String> loadGames() {
		log.info("Consultando API do Spotify para o retorno do link de autenticação");		
		return ResponseEntity.ok(this.apiSportifyService.getLinkAutorization());
	}
	
	/**
	 * Retorna o codigo de autenticação da API do spotify
	 * @param model
	 * @param code
	 * @return
	 */
   @GetMapping(value="/callback")
    public String setSeveralAlbums(@RequestParam(value="code") String code) {
        this.albumsService.setSeveralAlbums(code.trim());
        return "Base alimentada com os albums";
        
    }
   

  

}
