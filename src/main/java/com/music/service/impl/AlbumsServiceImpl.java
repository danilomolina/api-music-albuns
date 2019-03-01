package com.music.service.impl;

import java.io.IOException;
import java.net.URI;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.music.entities.CatalogDisk;
import com.music.service.AlbumsService;
import com.music.service.CatalogDiskService;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.SpotifyHttpManager;
import com.wrapper.spotify.enums.ModelObjectType;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import com.wrapper.spotify.model_objects.special.SearchResult;
import com.wrapper.spotify.model_objects.specification.AlbumSimplified;
import com.wrapper.spotify.model_objects.specification.ArtistSimplified;
import com.wrapper.spotify.requests.authorization.authorization_code.AuthorizationCodeRequest;
import com.wrapper.spotify.requests.data.search.SearchItemRequest;

@Service
public class AlbumsServiceImpl implements AlbumsService{
	
	  @Autowired
	  private CatalogDiskService diskService; 

	  private static final String clientId = "8d5a38f8600e41b693a75a50021ba987";
	  private static final String clientSecret = "551c38984f4349f2aa8d5cead30cbf98";
	  private static final URI redirectUri = SpotifyHttpManager.makeUri("http://localhost:8080/api/music/v1/callback");
	
	  private static final String type = ModelObjectType.ALBUM.getType();
	  
	  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
	          .setClientId(clientId)
	          .setClientSecret(clientSecret)
	          .setRedirectUri(redirectUri)
	          .build();

	  @Override
	  public void setSeveralAlbums(String code) {		  
		  try { 

			 //pega o codigo de autorização para se conectar na API da spotify
			 final AuthorizationCodeRequest authorizationCodeRequest = spotifyApi.authorizationCode(code).build();
			 final AuthorizationCodeCredentials authorizationCodeCredentials = authorizationCodeRequest.execute();
			 
			 //Conecta na API com o codigo de autorização
			 final SpotifyApi spotifyApi = new SpotifyApi.Builder()
			          .setAccessToken(authorizationCodeCredentials.getAccessToken())
			          .build();	
			 
			 String[] genre = new String[4];
			 genre[0] = "pop";
			 genre[1] = "mpb";
			 genre[2] = "classic";
			 genre[3] = "rock";
			 
			 int count = 1;
			 
			 for (String genres : genre) {

				 //Busca albums pelo genero		  
				 final SearchItemRequest searchItemRequest = spotifyApi.searchItem(genres, type)
				          .limit(50)
				          .build();
					 
				 final Future<SearchResult> searchResultFuture = searchItemRequest.executeAsync();
				 final SearchResult searchResult = searchResultFuture.get();
				 
				 AlbumSimplified[] albums = searchResult.getAlbums().getItems();
	
				 for (AlbumSimplified albumSimplified : albums) {				 
					 CatalogDisk disk =  new CatalogDisk();
					 
					 ArtistSimplified[] artist = albumSimplified.getArtists();
					 
					 disk.setGenre(genres);
					 disk.setName(albumSimplified.getName());
					 disk.setPrice(65.90 + count);
					 disk.setArtist(artist[0].getName());
					 diskService.save(disk);
					 count ++;
				}
			}
	

	    } catch (SpotifyWebApiException | IOException | InterruptedException | ExecutionException  e) {
	      System.out.println("Error: " + e.getCause().getMessage());
	    }
	  }
	
}
