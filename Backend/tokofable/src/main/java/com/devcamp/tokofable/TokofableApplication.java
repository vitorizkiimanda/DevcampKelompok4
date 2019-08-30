package com.devcamp.tokofable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableConfigurationProperties
@EnableCaching
public class TokofableApplication {

	public static void main(String[] args) {
		SpringApplication.run(TokofableApplication.class, args);
	}

}
