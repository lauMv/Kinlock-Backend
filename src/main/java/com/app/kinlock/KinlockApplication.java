package com.app.kinlock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.app.kinlock.data")
public class KinlockApplication {

	public static void main(String[] args) {
		BCryptPasswordEncoder enc = new BCryptPasswordEncoder();
		System.out.println(enc.matches("admin",
				"$2a$10$slVK/Rh3dGHKHqZWrFZrKu1zRgKq.UJ0Ku/A0qPHXXSp5yQBMFPlG"));
		System.out.println(new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder()
				.encode("admin"));
		SpringApplication.run(KinlockApplication.class, args);
	}

}
