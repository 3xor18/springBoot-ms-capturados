package com.fakecompany.mydemoapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class MydemoappApplication {

	public static void main(String[] args) {
		SpringApplication.run(MydemoappApplication.class, args);
	}

}
