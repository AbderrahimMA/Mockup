package com.otp.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableJpaRepositories("com.otp.repo")
@EntityScan("com.otp.model")
@SpringBootApplication(scanBasePackages = { "com.otp" })

public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
