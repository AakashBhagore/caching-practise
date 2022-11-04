package com.caching.practise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching //Enable caching on application layer
public class CachingPractiseApplication {

	public static void main(String[] args) {
		SpringApplication.run(CachingPractiseApplication.class, args);
	}

}
