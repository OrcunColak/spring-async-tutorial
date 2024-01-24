package com.colak.springasynctutorial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SpringAsyncTutorialApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringAsyncTutorialApplication.class, args);
	}

}
