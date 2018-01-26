package com.rookie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class RookieApplication {

	public static void main(String[] args) {
		SpringApplication.run(RookieApplication.class, args);
	}
}
