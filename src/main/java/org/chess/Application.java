package org.chess;

import org.aspectj.lang.annotation.AdviceName;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.springframework.context.annotation.EnableLoadTimeWeaving.AspectJWeaving.ENABLED;

@SpringBootApplication(scanBasePackages = {"org.chess.*", "org.chess.library.*"})
@EnableJpaRepositories
@EnableAspectJAutoProxy
@EnableScheduling
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(org.chess.Application.class, args);
	}

}
