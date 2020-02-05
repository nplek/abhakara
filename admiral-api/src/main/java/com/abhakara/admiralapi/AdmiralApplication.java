package com.abhakara.admiralapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class AdmiralApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdmiralApplication.class, args);
	}

}
