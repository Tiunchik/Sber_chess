package org.chess;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.springframework.context.annotation.EnableLoadTimeWeaving.AspectJWeaving.ENABLED;

@SpringBootApplication
@EnableJpaRepositories
@EnableScheduling
@EnableWebMvc
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(org.chess.Application.class, args);
	}

}
