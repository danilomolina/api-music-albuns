package com.music.service.impl;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.SpotifyHttpManager;
import com.wrapper.spotify.requests.authorization.authorization_code.AuthorizationCodeUriRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.springframework.stereotype.Service;

import com.music.service.AuthorizationAPISpotify;

@Service
public class AuthorizationAPISpotifyImpl implements AuthorizationAPISpotify{
	
	  private static final String clientId = "8d5a38f8600e41b693a75a50021ba987";
	  private static final String clientSecret = "551c38984f4349f2aa8d5cead30cbf98";
	  private static final URI redirectUri = SpotifyHttpManager.makeUri("http://localhost:8080/api/music/v1/callback");
	  private static final Logger log = LoggerFactory.getLogger(AuthorizationAPISpotifyImpl.class);

	  /**
	   * Passa as credencias do usuário para a API do spotify 
	   */
	  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
	          .setClientId(clientId)
	          .setClientSecret(clientSecret)
	          .setRedirectUri(redirectUri)
	          .build();
	  
	  /**
	   * Monta a chamado na API do spotify
	   */
	  private static final AuthorizationCodeUriRequest authorizationCodeUriRequest = spotifyApi.authorizationCodeUri()
	          .scope("user-read-birthdate,user-read-email")
	          .show_dialog(true)
	          .build();

	  /**
	   * Retorna o Link para fazer a autenticação no Spotify
	   * @return
	   */
	  public String getLinkAutorization() {
	    try {
	      log.info("Pegando o link de autenticação na API Spotify");
	      final Future<URI> uriFuture = authorizationCodeUriRequest.executeAsync();

	      final URI uri = uriFuture.get();

	      return uri.toString();
	    } catch (InterruptedException | ExecutionException e) {
	      log.info("Error: " + e.getCause().getMessage());
	    }
		return null;
	  }


}
