package com.learning.oldbatch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing
@SpringBootApplication
public class OldbatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(OldbatchApplication.class, args);
	}

}
