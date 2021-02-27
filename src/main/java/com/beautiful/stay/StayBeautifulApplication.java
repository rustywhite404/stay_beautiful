package com.beautiful.stay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class}) //스프링시큐리티 제공 로그인 기능 사용하지 않음
public class StayBeautifulApplication {

	public static void main(String[] args) {
		System.setProperty("spring.devtools.restart.enabled","true");
		System.setProperty("spring.devtools.livereload.enabled","true");
		SpringApplication.run(StayBeautifulApplication.class, args);
	}

}
