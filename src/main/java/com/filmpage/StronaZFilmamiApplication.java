package com.filmpage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.r2dbc.R2dbcAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = {R2dbcAutoConfiguration.class})
@EnableJpaRepositories

public class StronaZFilmamiApplication {

	public static void main(String[] args) {
		SpringApplication.run(StronaZFilmamiApplication.class, args);
	}

}
