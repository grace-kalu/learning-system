package com.codeWithMerald.RoyalSealLearningSystem;

import com.codeWithMerald.RoyalSealLearningSystem.security.JwtAuthenticationFilter;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
public class RoyalSealLearningSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(RoyalSealLearningSystemApplication.class, args);
	}

	@PostConstruct
	void init() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
