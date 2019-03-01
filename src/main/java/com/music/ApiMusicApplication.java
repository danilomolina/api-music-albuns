package com.music;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;


@SpringBootApplication
public class ApiMusicApplication {
	
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ApiMusicApplication.class);
    }

	public static void main(String[] args) {
		SpringApplication.run(ApiMusicApplication.class, args);
	}

}
