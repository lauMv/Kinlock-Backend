package com.app.kinlock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.app.kinlock.data")
public class KinlockApplication {

	public static void main(String[] args) {
		SpringApplication.run(KinlockApplication.class, args);
	}

}
